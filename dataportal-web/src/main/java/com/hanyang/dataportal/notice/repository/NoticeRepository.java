package com.hanyang.dataportal.notice.repository;

import com.hanyang.dataportal.notice.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {
    Page<Notice> findAll(Pageable pageable);

    @Query("select n from Notice n join fetch User u where n.noticeId = :noticeId")
    Optional<Notice> findByIdWithAdmin(Long noticeId);

}
