package com.fevi.music.top100.controller.view;

import com.fevi.music.top100.domain.MusicRankInfo;
import com.fevi.music.top100.domain.SingerRank;
import com.fevi.music.top100.domain.SongRank;
import com.fevi.music.top100.repository.MusicRankInfoRepository;
import com.fevi.music.top100.repository.SingerRankRepository;
import com.fevi.music.top100.repository.SongRankRepository;
import com.fevi.music.top100.service.MusicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 7. 10.
 */
@Controller
public class MusicRankViewController {

    @Autowired private SongRankRepository songRankRepository;
    @Autowired private SingerRankRepository singerRankRepository;
    @Autowired private MusicRankInfoRepository musicRankInfoRepository;

    @Autowired private MusicInfoService musicInfoService;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/song");
    }

    @RequestMapping("/song")
    public String songPage(Model model) {
        Page<SongRank> songRankPage = songRankRepository.findAll(new PageRequest(0, 100));
        model.addAttribute("musics", songRankPage.getContent());
        model.addAttribute("title", "Music");
        return "song";
    }

    @RequestMapping("/song/{songId}")
    public String songDetail(@PathVariable Long songId, Model model) {
        List<MusicRankInfo> songs = musicRankInfoRepository.findBySongId(songId);
        if(songs.isEmpty()) {
            return "404";
        }
        model.addAttribute("title", songs.get(0).getSongName());
        model.addAttribute("sum", songs.stream().mapToLong(MusicRankInfo::getScore).sum());

        model.addAttribute("songs", songs);
        return "songDetail";
    }

    @RequestMapping("/singer")
    public String songDetail(Model model) {
        model.addAttribute("title", "Singer");
        return "singers";
    }

    @RequestMapping("/singer/{singerId}")
    public String singerDetail(@PathVariable Long singerId, Model model) {
        List<MusicRankInfo> songs = musicRankInfoRepository.findBySingerId(singerId);
        if(songs.isEmpty()) {
            return "404";
        }
        model.addAttribute("title", songs.get(0).getSinger());
        model.addAttribute("sum", songs.stream().mapToLong(MusicRankInfo::getScore).sum());

        model.addAttribute("songs", songs);
        return "singerDetail";
    }

    @RequestMapping("/album")
    public String album(Model model) {
        model.addAttribute("title", "Album");
        return "album";
    }

    @RequestMapping("/album/{albumId}")
    public String albumDetail(@PathVariable Long albumId, Model model) {
        List<MusicRankInfo> songs = musicRankInfoRepository.findByAlbumId(albumId);
        if(songs.isEmpty()) {
            return "404";
        }
        model.addAttribute("title", songs.get(0).getAlbum());
        model.addAttribute("sum", songs.stream().mapToLong(MusicRankInfo::getScore).sum());

        model.addAttribute("songs", songs);
        return "albumDetail";
    }
}
