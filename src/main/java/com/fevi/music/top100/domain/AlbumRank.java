package com.fevi.music.top100.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 7. 16.
 */
@Entity
public class AlbumRank {

    @Id
    private Long rank;

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

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "AlbumRank{" +
                "rank=" + rank +
                ", singerId=" + singerId +
                ", singer='" + singer + '\'' +
                ", albumId=" + albumId +
                ", album='" + album + '\'' +
                ", albumImage='" + albumImage + '\'' +
                ", score=" + score +
                '}';
    }
}
