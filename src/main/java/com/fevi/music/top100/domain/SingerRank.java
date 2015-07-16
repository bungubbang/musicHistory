package com.fevi.music.top100.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 7. 16.
 */
@Entity
public class SingerRank {

    @Id
    private Long rank;

    private Long singerId;
    private String singer;
    private String singerImage;

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

    public String getSingerImage() {
        return singerImage;
    }

    public void setSingerImage(String singerImage) {
        this.singerImage = singerImage;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "SingerRank{" +
                "rank=" + rank +
                ", singerId=" + singerId +
                ", singer='" + singer + '\'' +
                ", singerImage='" + singerImage + '\'' +
                ", score=" + score +
                '}';
    }
}
