package com.kopo.spring.controller;

import com.kopo.spring.chat.ChatHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatController {


    @RequestMapping("/intro")
    public String intro() {
        return "/chat/intro";
    }

    @RequestMapping("/room")
    public String room() {
        return "/chat/chatroom";
    }

    @RequestMapping("/roomList")
    @ResponseBody
    public Set<String> roomList() {
        log.info(this.getClass().getName() + ".roomList Start!");

        log.info(this.getClass().getName() + ".roomList End!");

        return ChatHandler.roomInfo.keySet();
    }
}
