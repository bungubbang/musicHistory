package com.fevi.music.top100.controller.api;

import com.fevi.music.top100.domain.*;
import com.fevi.music.top100.repository.AlbumRankRepository;
import com.fevi.music.top100.repository.MusicRankInfoRepository;
import com.fevi.music.top100.repository.SingerRankRepository;
import com.fevi.music.top100.repository.SongRankRepository;
import com.fevi.music.top100.service.MelonMusicHistoryParse;
import com.fevi.music.top100.service.MusicInfoService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 7. 3.
 */
@RestController
@RequestMapping("api")
public class MusicApiController {

    private static final Log logger = LogFactory.getLog(MusicApiController.class);


    @Autowired private SongRankRepository songRankRepository;
    @Autowired private SingerRankRepository singerRankRepository;
    @Autowired private AlbumRankRepository albumRankRepository;
    @Autowired private MusicRankInfoRepository musicRankInfoRepository;

    @Autowired private MusicInfoService musicInfoService;


    @RequestMapping(value = "song", produces = "application/json", method = RequestMethod.GET)
    public Page<SongRank> songRanks(Pageable pageable) {
        logger.debug("path = song, pageable = [" + pageable + "]");
        return songRankRepository.findAll(pageable);
    }

    @RequestMapping(value = "singer", produces = "application/json", method = RequestMethod.GET)
    public Page<SingerRank> singerRanks(Pageable pageable) {
        logger.debug("path = singer, pageable = [" + pageable + "]");
        return singerRankRepository.findAll(pageable);
    }

    @RequestMapping(value = "album", produces = "application/json", method = RequestMethod.GET)
    public Page<AlbumRank> albumRanks(Pageable pageable) {
        logger.debug("path = albums, pageable = [" + pageable + "]");
        return albumRankRepository.findAll(pageable);
    }


    @RequestMapping(value = "graph", produces = "application/json", method = RequestMethod.GET)
    public GraphData getGraphData(@RequestParam Optional<Long> songId) {

        logger.debug("path = search, songId = [" + songId + "]");

        if(songId.isPresent()) {
            return musicInfoService.getSongGraphData(songId.get());
        }

        return null;
    }

    @RequestMapping(value = "graph", produces = "application/json", method = RequestMethod.GET)
    public List<GraphData> getGraphDataList(
                                  @RequestParam Optional<Long> singerId,
                                  @RequestParam Optional<Long> albumId) {

        logger.debug("path = search, singerId = [" + singerId + "], albumId = [" + albumId + "]");

        List<GraphData> graphDatas = new ArrayList<>();
        List<MusicRankInfo> musicInfos = new ArrayList<>();
        Set<Long> songIds = new HashSet<>();
        if(singerId.isPresent()) {
            musicInfos = musicRankInfoRepository.findBySingerId(singerId.get());
        }else if(albumId.isPresent()) {
            musicInfos = musicRankInfoRepository.findByAlbumId(albumId.get());
        }

        for (MusicRankInfo musicInfo : musicInfos) {
            songIds.add(musicInfo.getSongId());
        }

        for (Long songId : songIds) {
            graphDatas.add(musicInfoService.getSongGraphData(songId));
        }

        return graphDatas;
    }


}
