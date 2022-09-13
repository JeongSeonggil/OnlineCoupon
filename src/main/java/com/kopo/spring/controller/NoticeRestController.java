package com.kopo.spring.controller;

import com.kopo.spring.dto.NoticeDto;
import com.kopo.spring.service.NoticeService;
import com.kopo.spring.vo.RegNoticeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NoticeRestController {
    private final NoticeService noticeService;


    @GetMapping("/notices")
    public ResponseEntity<List<NoticeDto>> getNoticeList() throws Exception {
        List<NoticeDto> resultList = noticeService.getNoticeList();

        resultList.forEach(noticeDto -> {
            try {
                noticeDto.add(linkTo(methodOn(NoticeRestController.class).getOneNotice(noticeDto.getNoticeSeq()))
                        .withRel("find One Notice Information"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        return ResponseEntity.ok().body(resultList);

    }

    @GetMapping("/notices/{noticeSeq}")
    public ResponseEntity<NoticeDto> getOneNotice(@PathVariable final long noticeSeq) throws Exception {

        NoticeDto result = noticeService.getOneNotice(noticeSeq);
        result.add(linkTo(methodOn(NoticeRestController.class).modifyNotice(noticeSeq, RegNoticeRequest.builder().build())).withRel("update Notice Information"));

        result.add(linkTo(methodOn(NoticeRestController.class).deleteNoticeBySeq(result.getNoticeSeq(), "{userId}"))
                .withRel("delete Notice"));

        return ResponseEntity.ok().body(result);

    }

    @PostMapping("/notices")
    public ResponseEntity<NoticeDto> saveNotice(@RequestBody NoticeDto noticeDto) throws Exception {

        NoticeDto result = noticeService.saveNotice(noticeDto);
        result.add(linkTo(methodOn(NoticeRestController.class).getOneNotice(result.getNoticeSeq()))
                .withRel("find One Notice Information"));

        result.add(linkTo(methodOn(NoticeRestController.class).getNoticeList())
                .withRel("go to NoticeList"));

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PatchMapping("/notices/{noticeSeq}")
    public ResponseEntity<NoticeDto> modifyNotice(
            @PathVariable final Long noticeSeq, @RequestBody final RegNoticeRequest request) throws Exception {

        request.setNoticeSeq(noticeSeq);

        NoticeDto result = noticeService.modifyNotice(NoticeDto.builder()
                .noticeSeq(request.getNoticeSeq())
                .noticeYN(request.getNoticeYN())
                .title(request.getTitle())
                .userId(request.getUserId())
                .contents(request.getContents()).build());

        result.add(linkTo(methodOn(NoticeRestController.class).getOneNotice(result.getNoticeSeq()))
                .withRel("find One Notice Information"));

        return ResponseEntity.ok().body(result);

    }


    @DeleteMapping("/notices/{noticeSeq}")
    public ResponseEntity<String> deleteNoticeBySeq(@PathVariable final Long noticeSeq, @RequestParam("userId") String userId) throws Exception {

        String result = noticeService.deleteNotice(noticeSeq, userId);

        return ResponseEntity.ok().body(result);

    }



}
