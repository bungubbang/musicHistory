package com.fevi.music.top100.repository;

import com.fevi.music.top100.domain.SongRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 7. 13.
 */
@Repository
public interface SongRankRepository extends JpaRepository<SongRank, Long> {
}
