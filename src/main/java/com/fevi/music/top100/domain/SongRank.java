package com.fevi.music.top100.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 7. 6.
 */
@Entity
public class SongRank {

    @Id
    private Long rank;

    private Long songId;
    private String songName;

    private Long singerId;
    private String singer;

    private Long albumId;
    private String album;
    private String albumImage;

    private Long score;

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
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

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    @Override
    public String toString() {
        return "SongRank{" +
                "rank=" + rank +
                ", songId=" + songId +
                ", songName='" + songName + '\'' +
                ", singerId=" + singerId +
                ", singer='" + singer + '\'' +
                ", albumId=" + albumId +
                ", album='" + album + '\'' +
                ", score=" + score +
                '}';
    }
}
