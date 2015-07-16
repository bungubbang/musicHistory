package com.fevi.music.top100.repository;

import com.fevi.music.top100.domain.SingerRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 7. 16.
 */
@Repository
public interface SingerRankRepository extends JpaRepository<SingerRank, Long> {
}
