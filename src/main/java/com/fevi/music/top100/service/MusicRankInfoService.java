package com.fevi.music.top100.service;

import com.fevi.music.top100.domain.GraphData;
import com.fevi.music.top100.domain.GraphValue;
import com.fevi.music.top100.domain.MusicRankInfo;
import com.fevi.music.top100.repository.MusicRankInfoRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

/**
 * Created by Minyumi on 15. 7. 23..
 */
@Service
public class MusicRankInfoService implements MusicInfoService {

    @Autowired private MusicRankInfoRepository musicRankInfoRepository;

    @Override
    public GraphData getSongGraphData(Long songId) {
        return generateGraphData(musicRankInfoRepository.findBySongId(songId));
    }

    private GraphData generateGraphData(List<MusicRankInfo> infos) {

        IntSummaryStatistics intSummaryStatistics = infos.stream().mapToInt(MusicRankInfo::getRankDate).summaryStatistics();
        Integer minDate = calculateMinDate(intSummaryStatistics.getMin());
        Integer maxDate = calculateMaxDate(intSummaryStatistics.getMax());
        ArrayList<Integer> xkeys = Lists.newArrayList();
        for (Integer i = minDate; i < maxDate;) {
            xkeys.add(i);
            if(Integer.valueOf(String.valueOf(i).substring(4)) < 12) {
                i++;
            } else  {
                i = i + 100 - 11;
            }
        }


        List<GraphValue> graphValues = Lists.newArrayList();
        infos.stream().map(MusicRankInfo::getSongName).distinct().forEach(songName -> graphValues.add(new GraphValue(songName)));

        graphValues.stream().forEach(v -> xkeys.stream().forEach(key -> {

            Optional<MusicRankInfo> info = infos.stream().filter(musicRankInfo -> musicRankInfo.getSongName().equals(v.getName())
                    && musicRankInfo.getRankDate().equals(key)).findFirst();
            if (info.isPresent()) {
                v.getData().add(info.get().getRank());
            } else {
                v.getData().add(100);
            }
        }));

        return new GraphData(xkeys, graphValues);
    }

    public Integer calculateMinDate(Integer date) {
        if(Integer.valueOf(String.valueOf(date).substring(4)) < 4) {
            date = date - 89;
        }
        return date - 3;
    }

    public Integer calculateMaxDate(Integer date) {
        if(Integer.valueOf(String.valueOf(date).substring(4)) > 9) {
            date = date + 89;
        }
        return date + 3;
    }

}
