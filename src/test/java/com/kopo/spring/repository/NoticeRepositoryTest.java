package com.kopo.spring.repository;

import com.kopo.spring.enums.NoticeType;
import com.kopo.spring.repository.entity.NoticeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // test in mariaDB
public class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository target;

    @DisplayName("공지 사항 등록 성공")
    @Test
    void test1() {
        NoticeEntity request = getNoticeEntity();


        NoticeEntity result = target.save(request);

        assertThat(result.getNoticeSeq()).isEqualTo(1L);
        assertThat(result.getUserId()).isEqualTo(request.getUserId());

    }
    NoticeEntity getNoticeEntity() {
        return NoticeEntity.builder()
                .userId("userId")
                .noticeYN(NoticeType.Y)
                .title("title")
                .contents("contents")
                .chgId("userId")
                .chgDt("20220830")
                .regId("userId")
                .readCnt(1L)
                .regDt("20220830").build();
    }
}
