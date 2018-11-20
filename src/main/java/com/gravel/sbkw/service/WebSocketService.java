package com.gravel.sbkw.service;

import com.gravel.sbkw.commons.TopicEnums;
import com.gravel.sbkw.commons.JsonHelper;
import com.gravel.sbkw.vo.Message;
import com.gravel.sbkw.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Gravel
 * @date 2018/11/20.
 */
@Service
public class WebSocketService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Response send(@RequestBody Message reqVO) {

        logger.info("get req msg: {}", reqVO.getMessage());

        String message = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                + " 收到一条消息: " + reqVO.getMessage();

        Response response = new Response(message);
        messagingTemplate.convertAndSend(TopicEnums.WEBSOCKET_CHANNEL.getTopic(), response);

        logger.info("send ws msg: {}", JsonHelper.toJson(response).toString());

        return response;
    }

}
