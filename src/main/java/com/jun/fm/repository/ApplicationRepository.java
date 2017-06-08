package com.jun.fm.repository;

import com.jun.fm.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wayne on 2017. 6. 6..
 *
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
