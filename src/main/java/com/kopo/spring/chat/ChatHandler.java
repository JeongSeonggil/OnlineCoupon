package com.kopo.spring.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kopo.spring.dto.ChatDto;
import com.kopo.spring.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {
    // 웹소켓에서 접속되는 사용자들을 저장하며, 중복을 제거하기 위해 Set 데이터 구조 사용
    private static Set<WebSocketSession> clients = Collections.synchronizedSet(new LinkedHashSet<>());

    // 채팅룸 조회하기 위해 사용
    public static Map<String, String> roomInfo = Collections.synchronizedMap(new LinkedHashMap<>());


    // 최초 사용자와 웹 소켓이 연결될 때 실행
    // 사용자 접속 정보를 세션에 저장
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        log.info(this.getClass().getName() + ".afterConnectionEstablished Start!");
        String roomName = String.valueOf(session.getAttributes().get("roomName"));
        String userName = String.valueOf(session.getAttributes().get("userName"));
        String roomNameHash = String.valueOf(session.getAttributes().get("roomNameHash"));

        log.info("roomName : " + roomName + "| userName : " + userName);
        // 웹소켓에 접속된 모든 사용자 검색
        clients.forEach(client -> {
            try {
                ChatDto chatDto = new ChatDto();
                chatDto.setName("관리자");
                chatDto.setMsg(userName + "님이" + roomName + "채팅방에 입장하셨습니다");
                chatDto.setDate(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));

                String json = new ObjectMapper().writeValueAsString(chatDto);
                log.info("json : " + json);

                TextMessage chatMsg = new TextMessage(json);
                client.sendMessage(chatMsg);

            } catch (IOException e) {
                log.warn("IOException : " + e);
            }
        });

        if (!clients.contains(session)) {
            clients.add(session); // 접속된 세션 저장
            roomInfo.put(roomName, roomNameHash); // 생성된 채팅룸 이름 저장

            log.info("session open : " + session);

        }
        log.warn(this.getClass().getName() + ".afterConnectionEstablished End!");

        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(this.getClass().getName() + ".afterConnectionClosed Start!");

        String roomName = String.valueOf(session.getAttributes().get("roomName"));
        String userName = String.valueOf(session.getAttributes().get("userName"));
        String roomNameHash = String.valueOf(session.getAttributes().get("roomNameHash"));

        clients.remove(session);

        clients.forEach(client -> {
            if (roomNameHash.equals(client.getAttributes().get("roomNameHash"))) {
                try {
                    ChatDto chatDto = new ChatDto();
                    chatDto.setName("관리자");
                    chatDto.setMsg(userName + "님이" + roomName + "채팅방에 퇴장하셨습니다.");
                    chatDto.setDate(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));

                    String json = new ObjectMapper().writeValueAsString(chatDto);

                    TextMessage chatMsg = new TextMessage(json);

                    client.sendMessage(chatMsg);

                    chatDto = null;

                } catch (IOException e) {
                    log.warn("IOException : " + e);

                }
            }
        });

        log.info(this.getClass().getName() + ".afterConnectionClosed End!");
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info(this.getClass().getName() + ".handleTextMessage Start!");

        String roomName = String.valueOf(session.getAttributes().get("roomName"));
        String userName = String.valueOf(session.getAttributes().get("userName"));
        String roomNameHash = String.valueOf(session.getAttributes().get("roomNameHash"));

        log.info("roomName : " + roomName);
        log.info("userName : " + userName);
        log.info("roomNameHash : " + roomNameHash);

        String msg = String.valueOf(message.getPayload());
        log.info("msg : " + msg);

        ChatDto chatDto = new ObjectMapper().readValue(msg, ChatDto.class);

        chatDto.setDate(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));

        String json = new ObjectMapper().writeValueAsString(chatDto);

        log.info("json : " + json);

        clients.forEach(client -> {
            if (roomNameHash.equals(client.getAttributes().get("roomNameHash"))) {
                try {
                    TextMessage chatMsg = new TextMessage(json);
                    client.sendMessage(chatMsg);

                } catch (IOException e) {
                    log.warn("IOException : " + e);
                }
            }
        });

        log.info(this.getClass().getName() + ".handleTextMessage Start!");

        super.handleTextMessage(session, message);
    }
}
