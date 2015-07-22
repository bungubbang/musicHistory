package com.fevi.music.top100.batch;

import com.fevi.music.top100.domain.GraphValue;
import com.fevi.music.top100.domain.MusicRankInfo;
import com.fevi.music.top100.service.MelonMusicHistoryParse;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 5. 21.
 */
public class MelonMusicHistoryParseTest {

    MelonMusicHistoryParse melonMusicHistoryParse;

    @Before
    public void setUp() throws Exception {
        melonMusicHistoryParse = new MelonMusicHistoryParse();

    }

    @Test
    public void testMelonHistorySong() throws Exception {
        List<MusicRankInfo> musicRankInfos = melonMusicHistoryParse.parse("2015", "06");
        for (MusicRankInfo musicRankInfo : musicRankInfos) {
            System.out.println("musicRankInfo = " + musicRankInfo);
        }
    }

    @Test
    public void testDummy() {

        MusicRankInfo musicRankInfo1 = new MusicRankInfo();
        musicRankInfo1.setRankDate(201407);
        musicRankInfo1.setRank(3);
        musicRankInfo1.setSongId(12l);
        musicRankInfo1.setSongName("1track");

        MusicRankInfo musicRankInfo2 = new MusicRankInfo();
        musicRankInfo2.setRankDate(201410);
        musicRankInfo2.setRank(16);
        musicRankInfo2.setSongId(13l);
        musicRankInfo2.setSongName("2track");

        MusicRankInfo musicRankInfo3 = new MusicRankInfo();
        musicRankInfo3.setRankDate(201411);
        musicRankInfo3.setRank(16);
        musicRankInfo3.setSongId(13l);
        musicRankInfo3.setSongName("2track");

        List<MusicRankInfo> dtos = Lists.newArrayList(musicRankInfo2, musicRankInfo3);



        IntSummaryStatistics intSummaryStatistics = dtos.stream().mapToInt(MusicRankInfo::getRankDate).summaryStatistics();
        Integer minDate = caculateMinDate(intSummaryStatistics.getMin());
        Integer maxDate = caculateMaxDate(intSummaryStatistics.getMax());
        ArrayList<Integer> xkeys = Lists.newArrayList();
        for (Integer i = minDate; i < maxDate;) {
            xkeys.add(i);
            if(Integer.valueOf(String.valueOf(i).substring(4)) < 12) {
                i++;
            } else  {
                i = i + 100 - 11;
            }
        }
        System.out.println("xkeys = " + xkeys);

        List<GraphValue> graphValues = Lists.newArrayList();
        dtos.stream().map(MusicRankInfo::getSongName).distinct().forEach(songName -> graphValues.add(new GraphValue(songName)));

        graphValues.stream().forEach(v -> {
            xkeys.stream().forEach(key -> {

                Optional<MusicRankInfo> info = dtos.stream().filter(musicRankInfo -> {
                    return musicRankInfo.getSongName().equals(v.getName())
                            && musicRankInfo.getRankDate().equals(key);
                }).findFirst();
                if (info.isPresent()) {
                    v.getData().add(info.get().getRank());
                }else {
                    v.getData().add(0);
                }
            });
        });

        System.out.println("graphValues = " + graphValues);
    }

    public Integer caculateMinDate(Integer date) {
        if(Integer.valueOf(String.valueOf(date).substring(4)) < 4) {
            date = date - 89;
        }
        return date - 3;
    }

    public Integer caculateMaxDate(Integer date) {
        if(Integer.valueOf(String.valueOf(date).substring(4)) > 9) {
            date = date + 89;
        }
        return date + 3;
    }

}