package com.gravel.sbkw.listener;

import com.gravel.sbkw.commons.TopicEnums;
import com.gravel.sbkw.commons.JsonHelper;
import com.gravel.sbkw.service.WebSocketService;
import com.gravel.sbkw.vo.Message;
import com.gravel.sbkw.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Gravel
 * @date 2018/11/20.
 */
@Component
public class ConsumerListener {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

    @Autowired
    private WebSocketService websocketService;

    @KafkaListener(topics = TopicEnums.Constants.KAFKA_LISTENER_TOPIC)
    public void consumer(String message) {

        logger.info("consumer topic string get : {}", message);

        Message messageReq = new Message();
        messageReq.setMessage(message);

        logger.info("send websocket request : {}", JsonHelper.toJson(messageReq).toString());

        Response response = websocketService.send(messageReq);

        logger.info("send websocket response : {}", JsonHelper.toJson(response).toString());

    }

}
