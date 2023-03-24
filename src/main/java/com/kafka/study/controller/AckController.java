package com.kafka.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author qcl
 * @Description
 * @Date 10:04 AM 3/24/2023
 */
@Slf4j
@RestController
public class AckController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 手动提交偏移量
     */
    @GetMapping("/hello")
    public String hello() throws Exception {
        // 发送消息
        for (int i = 0; i < 10; i++) {
            String message = "Message " + i;
            kafkaTemplate.send("topic1", message);
            log.info("Sent message: {}", message);
            Thread.sleep(1000);
        }
        return "hello";
    }


//    @KafkaListener(topics = "topic1", groupId = "my-group", containerFactory = "kafkaListenerContainerFactory")
//    public void onMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
//        log.info("listen Received message >>> {}", record);
//        acknowledgment.acknowledge();
//    }

    /**
     * 批量提交消费位移
     * @param records
     * @param acknowledgment
     */
    @KafkaListener(topics = "topic1", groupId = "my-group", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(List<ConsumerRecord<String, String>> records, Acknowledgment acknowledgment) {
        for (ConsumerRecord<String, String> record : records) {
            String message = record.value();
            // 处理消息
            log.info("onMessage: {}", message);
        }
        acknowledgment.acknowledge();
    }
}
