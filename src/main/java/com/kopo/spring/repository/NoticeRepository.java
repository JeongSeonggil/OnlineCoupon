package com.kopo.spring.repository;

import com.kopo.spring.repository.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE NOTICE A SET A.read_cnt = IFNULL(A.read_cnt, 0) + 1 WHERE A.noticeSeq = :noticeSeq",
            nativeQuery = true)
    int updateReadCnt(@Param("noticeSeq") Long noticeSeq);
}
