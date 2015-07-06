package com.fevi.music.top100.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 7. 3.
 */
@Entity
public class MusicRankInfo {

    @Id
    private Long id;

    private Integer year;
    private Integer month;

    private Integer rank;

    private Long songId;
    private String songName;

    private Long singerId;
    private String singer;

    private Long albumId;
    private String album;

    private String trimSong;
    private String trimSinger;

    private Long score;

    private Integer rankDate;


    public MusicRankInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getTrimSong() {
        return trimSong;
    }

    public void setTrimSong(String trimSong) {
        this.trimSong = trimSong;
    }

    public String getTrimSinger() {
        return trimSinger;
    }

    public void setTrimSinger(String trimSinger) {
        this.trimSinger = trimSinger;
    }

    public Integer getRankDate() {
        return rankDate;
    }

    public void setRankDate(Integer rankDate) {
        this.rankDate = rankDate;
    }

    @Override
    public String toString() {
        return "MusicRankInfo{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", rank=" + rank +
                ", songId=" + songId +
                ", songName='" + songName + '\'' +
                ", singerId=" + singerId +
                ", singer='" + singer + '\'' +
                ", albumId=" + albumId +
                ", album='" + album + '\'' +
                ", trimSong='" + trimSong + '\'' +
                ", trimSinger='" + trimSinger + '\'' +
                ", score=" + score +
                ", rankDate=" + rankDate +
                '}';
    }
}
