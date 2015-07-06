package com.fevi.music.top100.batch;

import com.fevi.music.top100.domain.MusicRankInfo;
import com.fevi.music.top100.service.MelonMusicHistoryParse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
}