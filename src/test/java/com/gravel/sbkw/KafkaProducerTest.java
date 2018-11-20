package com.gravel.sbkw;

import com.gravel.sbkw.commons.TopicEnums;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;
import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class KafkaProducerTest {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerTest.class);

    @Autowired
    Environment env;
    @Test
    public void producer() throws Exception {

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", env.getProperty("spring.kafka.bootstrap-servers"));
        properties.put("key.serializer", StringSerializer.class);
        properties.put("value.serializer", StringSerializer.class);

        KafkaProducer kafkaProducer = new KafkaProducer(properties);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TopicEnums.KAFKA_LISTENER_TOPIC.getTopic(),
                0, "message", "hello ws!");

        Future<RecordMetadata> future = kafkaProducer.send(producerRecord);

        RecordMetadata recordMetadata = future.get();


        logger.info("recordMetadata: {}", recordMetadata.toString());

    }

}
