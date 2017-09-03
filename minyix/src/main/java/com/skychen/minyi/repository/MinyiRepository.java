package com.skychen.minyi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MinyiRepository extends JpaRepository<Minyi, Integer> {

  @Query("select a from Minyi a where a.title like %:keyword% order by a.id asc")
  List<Minyi> findMinyis(@Param("keyword") String keyword);

}
