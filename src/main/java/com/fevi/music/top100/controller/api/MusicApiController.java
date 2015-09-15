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
    public Page<SongRank> songRanks(Pageable pageable, @RequestParam Optional<String> name) {
        if(name.isPresent()) {
            logger.debug("path = song, pageable = [" + pageable + "], search = " + name.get());
            return songRankRepository.findBySongNameLike("%" + name.get() + "%", pageable);
        }
        logger.debug("path = song, pageable = [" + pageable + "]");
        return songRankRepository.findAll(pageable);
    }

    @RequestMapping(value = "singer", produces = "application/json", method = RequestMethod.GET)
    public Page<SingerRank> singerRanks(Pageable pageable, @RequestParam Optional<String> name) {
        if(name.isPresent()) {
            logger.debug("path = singer, pageable = [" + pageable + "], search = " + name.get());
            return singerRankRepository.findBySingerLike("%" + name.get() + "%", pageable);
        }
        logger.debug("path = singer, pageable = [" + pageable + "]");
        return singerRankRepository.findAll(pageable);
    }

    @RequestMapping(value = "album", produces = "application/json", method = RequestMethod.GET)
    public Page<AlbumRank> albumRanks(Pageable pageable, @RequestParam Optional<String> name) {
        if(name.isPresent()) {
            logger.debug("path = albums, pageable = [" + pageable + "], search = " + name.get());
            return albumRankRepository.findByAlbumLike("%" + name.get() + "%", pageable);
        }
        logger.debug("path = albums, pageable = [" + pageable + "]");
        return albumRankRepository.findAll(pageable);
    }


    @RequestMapping(value = "graph", produces = "application/json", method = RequestMethod.GET)
    public GraphData getGraphData(@RequestParam Optional<Long> songId,
                                  @RequestParam Optional<Long> singerId,
                                  @RequestParam Optional<Long> albumId) {

        System.out.println("path = graph, songId = [" + songId + "], singerId = [" + singerId + "], albumId = [" + albumId + "]");

        if(songId.isPresent()) {
            return musicInfoService.getSongGraphData(songId.get());
        }else if(albumId.isPresent()) {
            return musicInfoService.getAlbumGraphData(albumId.get());
        }else if(singerId.isPresent()) {
            return musicInfoService.getSingerGraphData(singerId.get());
        }

        return null;
    }



}
