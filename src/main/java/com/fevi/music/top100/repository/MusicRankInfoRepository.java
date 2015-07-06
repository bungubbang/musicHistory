package com.fevi.music.top100.repository;

import com.fevi.music.top100.domain.MusicRankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 1000742
 * Email: sungyong.jung@sk.com
 * Date: 15. 7. 3.
 */
@Repository
public interface MusicRankInfoRepository extends JpaRepository<MusicRankInfo, Long> {
}
