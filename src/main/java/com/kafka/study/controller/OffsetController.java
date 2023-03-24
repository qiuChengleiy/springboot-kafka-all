//package com.kafka.study.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Author qcl
// * @Description
// * @Date 2:28 PM 3/21/2023
// */
//@Slf4j
//@RestController
//public class OffsetController {
//    @Autowired
//    private KafkaTemplate<Object, Object> kafkaTemplate;
//
//    @GetMapping("/hello")
//    public String hello() throws Exception {
//        // 发送消息
//        for (int i = 0; i < 10; i++) {
//            String message = "Message " + i;
//            kafkaTemplate.send("topic1", message);
//            log.info("Sent message: {}", message);
//            Thread.sleep(1000);
//        }
//        return "hello";
//    }
//
//    @KafkaListener(topics = "topic1", id = "to1")
//    public void listen1(String message) {
//        if(message.contains("6"))
//            throw new RuntimeException("系统异常");
//        log.info("listen1 Received message >>> {}", message);
//        kafkaTemplate.send("topic2", message);
//    }
//
//    @KafkaListener(topics = "topic2", id = "to2")
//    public void listen2(String message) {
//        if(String.format("Message %d", 6 + 1).equals(message)) {
//            log.error("消息丢失, 消息为 >>> {}", 6);
//        }
//        log.info("listen2 Received message >>> {}", message);
//    }
//
//    /**
//     * 手动提交偏移量
//     */
//    @GetMapping("/hello1")
//    public String hello1() throws Exception {
//        // 发送消息
//        for (int i = 0; i < 10; i++) {
//            String message = "Message " + i;
//            kafkaTemplate.send("topic3", message);
//            log.info("Sent message: {}", message);
//            Thread.sleep(1000);
//        }
//        return "hello1";
//    }
//
//
//    @KafkaListener(topics = "topic3", groupId = "my-group", containerFactory = "kafkaListenerContainerFactory")
//    public void onMessage(String message, Acknowledgment acknowledgment) {
//        log.info("listen1 Received message >>> {}", message);
////        if(message.contains("6"))
////            throw new RuntimeException("系统异常");
////        log.info("listen1 Received message >>> {}", message);
////
////        acknowledgment.acknowledge();
////        kafkaTemplate.send("topic2", message);
//    }
//
//}
