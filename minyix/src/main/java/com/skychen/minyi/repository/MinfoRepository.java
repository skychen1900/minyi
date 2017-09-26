package com.skychen.minyi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MinfoRepository extends JpaRepository<Minyi, Integer> {
  
  @Query("select a from Minfo a order by a.id desc")
  public Page<Minyi> findAll(Pageable pageable);

}