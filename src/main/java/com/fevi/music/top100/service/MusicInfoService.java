package com.fevi.music.top100.service;

import com.fevi.music.top100.domain.GraphData;

/**
 * Created by Minyumi on 15. 7. 23..
 */
public interface MusicInfoService {
    public GraphData getSongGraphData(Long songId);
    public GraphData getSingerGraphData(Long songId);
    public GraphData getAlbumGraphData(Long songId);
}
