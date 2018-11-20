package com.gravel.sbkw.controller;

import com.gravel.sbkw.commons.TopicEnums;
import com.gravel.sbkw.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gravel
 * @date 2018/11/20.
 */
@Controller
public class IndexController {

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    IndexController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * index页面展示
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @PostMapping("/send/message")
    @ResponseBody
    public String sendMessage(@RequestBody Message message) {
        kafkaTemplate.send(TopicEnums.KAFKA_LISTENER_TOPIC.getTopic(), message.getMessage());
        return message.getMessage();
    }
}
