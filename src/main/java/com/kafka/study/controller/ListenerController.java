//package com.kafka.study.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Author qcl
// * @Description
// * @Date 10:21 AM 3/15/2023
// */
//@Slf4j
//@RestController
//public class ListenerController {
//
//    @Autowired
//    private KafkaTemplate<Object, Object> kafkaTemplate;
//
//    @Transactional(rollbackFor = Exception.class)
//    @GetMapping("/hello")
//    public String hello() {
//        // 发送消息
//        kafkaTemplate.send("topic1", "1");
//        kafkaTemplate.send("topic2", "2");
//        return "hello";
//    }
//
//    /**
//     * 监听多个topic
//     * @param message
//     */
//    @KafkaListener(topics = {"topic1", "topic2"}, groupId = "group1")
//    public void listen(String message) {
//        log.info("Received message >>> {}", message);
//        // Received message >>> 1
//        // Received message >>> 2
//    }
//
//
//    @Transactional(rollbackFor = Exception.class)
//    @GetMapping("/hello1")
//    public String hello1() {
//        // 发送消息
//        kafkaTemplate.send("topic3", "3");
//        return "hello1";
//    }
//
//    @KafkaListener(topics = "topic3", containerFactory = "kafkaListenerContainerFactory")
//    public void processMessage(String message) {
//        // 处理消息
//        log.info("hello1 >>>>> {}", message);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @GetMapping("/hello2")
//    public String hello2() {
//        // 发送消息
//        kafkaTemplate.send("topic4", "4");
//        return "hello2";
//    }
//
//    @KafkaListener(id = "topic4Group", topics = "topic4", errorHandler = "myErrorHandler")
//    public void processMessage1(String message) {
//        // 处理消息
//        if(true)
//            throw new RuntimeException("消息处理异常");
//        log.info("hello2 >>>>> {}", message);
//    }
//}
