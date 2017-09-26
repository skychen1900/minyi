package com.skychen.minyi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
//@EnableJpaRepositories("com.skychen.minyi.repository")
public interface MinyiRepository extends JpaRepository<Minyi, Integer> {

  @Query("select a from Minyi a where a.title like %:keyword% order by a.minyiid desc")
  public Page<Minyi> findMinyis(Pageable pageable, @Param("keyword") String keyword);
  
  @Query("select a from Minyi a order by a.minyiid desc")
  public Page<Minyi> findAll(Pageable pageable);
  
  @Query("select count(*) from Minyi a where a.minyiid = ?1")
  public Integer countId(Integer minyiid);

  @Query("select a from Minyi a where a.minyiid = ?1 group by a.minyiid")
  public Minyi findOneMinyi(Integer minyiid);
  
  @Query("select a from Minyi a where a.status = ?1")
  public Minyi getStatusNum(String status);
  
  @Query("select a from Minyi a where a.status = ?1 and a.cdate =?2")
  public Minyi getStatusDateNum(String status, Timestamp timestamp);

}
