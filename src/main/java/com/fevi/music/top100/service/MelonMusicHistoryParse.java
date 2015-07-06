package com.fevi.music.top100.service;

import com.fevi.music.top100.domain.MusicRankInfo;
import com.fevi.music.top100.repository.MusicRankInfoRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired MusicRankInfoRepository musicRankInfoRepository;

    public List<MusicRankInfo> parse(String year, String month) {
        List<MusicRankInfo> musics = new ArrayList<>();

        String melon = "http://www.melon.com/chart/month/index.htm?idx=1&rankMonth=" + year + month;
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

        return musics;
    }


    private void mappingMusicInfo(List<MusicRankInfo> musics, Element element, String year, String month) {
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

        // Various Artists 때문에..
        if(a.size() > 2) {
            String artistId = a.get(1).attr("href").split("\'")[1];
            musicRankInfo.setSingerId(Long.valueOf(artistId));
        }

        String albumId = wrap_song_info.getElementsByClass("rank03").get(0).getElementsByTag("a").get(0).attr("href").split("\'")[1];
        musicRankInfo.setAlbumId(Long.valueOf(albumId));



        musicRankInfo.setId(Long.valueOf(year + month + songId));

        double rankPoint = (double) (101 - Integer.valueOf(rank));
        double powLeverage = 3;
        musicRankInfo.setScore((long) Math.pow(rankPoint, powLeverage));

        musicRankInfoRepository.save(musicRankInfo);

        logger.debug("insert music rank : " + musicRankInfo);
        musics.add(musicRankInfo);
    }


}
