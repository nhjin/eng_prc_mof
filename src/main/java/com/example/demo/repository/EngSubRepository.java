package com.example.demo.repository;

import com.example.demo.entity.EngSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface EngSubRepository extends JpaRepository<EngSub, Long> {

    @Query(nativeQuery = true, value = "SELECT e.text_sub FROM eng_sub e ORDER BY RAND() LIMIT 10")
    List<String> findFirstFiveTextSub();

    @Query(value = "SELECT k.start_time,  e.seq, k.text_sub as k_text_sub, e.text_sub as e_text_sub " +
            "FROM korean_sub k " +
            "INNER JOIN eng_sub e ON e.start_time = k.start_time " +
            "AND e.season = k.season " +
            "AND e.chapter = k.chapter " +
            "ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Map<String,Object>> findRandomSubtitles();


    @Query(value = "select k.start_time, k.text_sub  as k_text_sub, e.text_sub as e_text_sub from korean_sub k " +
            "inner join eng_sub e on e.start_time = k.start_time " +
            "and e.season = k.season " +
            "and e.chapter = k.chapter " +
            "where e.seq = :seq " + // 공백 추가
            "group by k.start_time, k.text_sub, e.text_sub", nativeQuery = true) // 모든 컬럼 추가
    Map<String,Object> findCustomDataBySeq(@Param("seq") String seq); //주석 테스트


}
