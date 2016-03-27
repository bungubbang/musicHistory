package com.fevi.music.top100.service;

import com.fevi.music.top100.domain.MusicRankInfo;
import com.fevi.music.top100.repository.MusicRankInfoRepository;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 5. 21.
 */
@Service
public class MelonMusicHistoryParse {

    private static final Logger logger = LoggerFactory.getLogger(MelonMusicHistoryParse.class);

    private static final String ALBUM_URL = "https://www.melon.com/album/detail.htm?albumId=";
    private static final String ARTIST_URL = "https://www.melon.com/artist/timeline.htm?artistId=";
    private static final String RANK_URL = "https://www.melon.com/chart/month/index.htm?idx=1&rankMonth=";

    @Autowired MusicRankInfoRepository musicRankInfoRepository;

    @Autowired JdbcTemplate jdbcTemplate;

    public List<MusicRankInfo> parse(String year, String month) {
        List<MusicRankInfo> musics = new ArrayList<>();

        String melon = RANK_URL + year + month;
        try {
            Document document = Jsoup.connect(melon).get();

            // Melon 1~50
            Elements lst501 = document.getElementsByClass("lst50");
            for (Element element : lst501) {
                mappingMusicInfo(musics, element, year, month);
            }

            // Melon 50~100
            Elements lst100 = document.getElementsByClass("lst100");
            for (Element element : lst100) {
                mappingMusicInfo(musics, element, year, month);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        jdbcTemplate.execute("TRUNCATE song_rank");
        jdbcTemplate.execute("TRUNCATE singer_rank");
        jdbcTemplate.execute("TRUNCATE album_rank");

        jdbcTemplate.execute("insert into song_rank (album, album_id, album_image, score, singer, singer_id, song_id, song_name) SELECT album, album_id, album_image, sum(score) as score, singer, singer_id, song_id, song_name FROM music_rank_info group by song_id order by sum(score) desc");
        jdbcTemplate.execute("insert into singer_rank (score, singer, singer_id, singer_image) SELECT sum(score) as score, singer, singer_id, singer_image FROM music_rank_info group by singer_id order by sum(score) desc");
        jdbcTemplate.execute("insert into album_rank (album, album_id, album_image, score, singer, singer_id) SELECT album, album_id, album_image, sum(score) as score, singer, singer_id FROM music_rank_info group by album_id order by sum(score) desc");

        return musics;
    }


    private void mappingMusicInfo(List<MusicRankInfo> musics, Element element, String year, String month) throws IOException {
        MusicRankInfo musicRankInfo = new MusicRankInfo();

        // date
        musicRankInfo.setYear(Integer.valueOf(year));
        musicRankInfo.setMonth(Integer.valueOf(month));
        musicRankInfo.setRankDate(Integer.parseInt(year + month));

        // rank
        String rank = element.getElementsByClass("rank").text();
        musicRankInfo.setRank(Integer.valueOf(rank));

        // song name
        Element wrap_song_info = element.getElementsByClass("wrap_song_info").get(0);
        String songName = wrap_song_info.getElementsByClass("rank01").get(0).text();
        String singer = wrap_song_info.getElementsByClass("rank02").get(0).getElementsByTag("span").get(0).text();
        String album = wrap_song_info.getElementsByClass("rank03").get(0).text();
        musicRankInfo.setSongName(songName);
        musicRankInfo.setSinger(singer);
        musicRankInfo.setAlbum(album);



        // trim info
        musicRankInfo.setTrimSong(songName.replaceAll("\\s", "").toLowerCase());
        musicRankInfo.setTrimSinger(singer.replaceAll("\\s", "").toLowerCase());

        // song id
        Elements a = wrap_song_info.getElementsByTag("a");
        String songId = element.getElementsByClass("btn_icon_detail").get(0).attr("href").split("\'")[1];
        musicRankInfo.setSongId(Long.valueOf(songId));

        String artistId = "";
        // Various Artists 때문에..
        if(a.size() > 2) {
            artistId = a.get(1).attr("href").split("\'")[1];
            musicRankInfo.setSingerId(Long.valueOf(artistId));
        }

        String albumId = wrap_song_info.getElementsByClass("rank03").get(0).getElementsByTag("a").get(0).attr("href").split("\'")[1];
        musicRankInfo.setAlbumId(Long.valueOf(albumId));


        // image
        Document document = Jsoup.connect(ALBUM_URL + albumId).get();
        String albumImageUrl = document.getElementsByClass("wrap_dtl_album").get(0).getElementsByClass("thumb").get(0).getElementsByTag("span").get(1).getElementsByTag("img").attr("src");
        musicRankInfo.setAlbumImage(albumImageUrl);

        if(!artistId.isEmpty()) {
            Document doc = Jsoup.connect(ARTIST_URL + artistId).get();
            String artistImageUrl = doc.getElementById("artistImgArea").getElementsByTag("img").attr("src");
            musicRankInfo.setSingerImage(artistImageUrl);
        }


        musicRankInfo.setId(Long.valueOf(year + month + songId));

        double rankPoint = (double) (101 - Integer.valueOf(rank));
        double powLeverage = 3;
        musicRankInfo.setScore((long) Math.pow(rankPoint, powLeverage));


        musicRankInfoRepository.save(musicRankInfo);
        logger.debug("insert music rank : " + musicRankInfo);


        musics.add(musicRankInfo);
    }

    @Scheduled(cron = "0 0 1 1 * ?")
    public void cronEveryMonth() {
        DateTime today = DateTime.now();
        int year = today.getYear();
        int month = today.getMonthOfYear();

        parse(String.valueOf(year), String.valueOf(month));
    }


}
