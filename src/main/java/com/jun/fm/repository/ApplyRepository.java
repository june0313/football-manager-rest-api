package com.jun.fm.repository;

import com.jun.fm.domain.apply.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wayne on 2017. 6. 6..
 *
 */
@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {
}
