package com.fevi.music.top100.controller;

import com.fevi.music.top100.service.MelonMusicHistoryParse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 7. 3.
 */
@RestController
@RequestMapping("api")
public class MusicApiController {

    private static final Log logger = LogFactory.getLog(MusicApiController.class);
    @Autowired
    MelonMusicHistoryParse melonMusicHistoryParse;

    @RequestMapping("insert")
    public String insert(String date) {
        String[] split = date.split("-");
        if(split.length == 1) {
            for (int i = 1; i <= 12; i++) {
                melonMusicHistoryParse.parse(date, String.format("%02d", i));
            }
        } else if(split.length == 2) {
                melonMusicHistoryParse.parse(split[0], split[1]);
        }
        return date;
    }

    @RequestMapping("insert/all")
    public String insertAll() {
        for(int j = 2004; j <= 2015; j++) {
            for (int i = 1; i <= 12; i++) {
                melonMusicHistoryParse.parse(String.valueOf(j), String.format("%02d", i));
            }
        }
        return "ok";
    }
}
