//package com.kafka.study.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Author qcl
// * @Description
// * @Date 10:08 AM 3/15/2023
// */
//@Slf4j
//@RestController
//public class SendToController {
//
//    @Autowired
//    private KafkaTemplate<Object, Object> kafkaTemplate;
//
//    // 接收消息
//    @Transactional(rollbackFor = Exception.class)
//    @KafkaListener(id = "input", topics = "inputTopic")
//    @SendTo("outputTopic")
//    public String processMessage(String message) {
//        // 处理消息并返回结果
//        log.info("inputTopic >>>> {}", message); // inputTopic >>>> 1
//        return "2";
//    }
//
//    @KafkaListener(id = "output", topics = "outputTopic")
//    public String process1Message(String message) {
//        // 处理消息并返回结果
//        String result = "Processed message: " + message;
//        log.info("outputTopic >>>> {}", result); // outputTopic >>>> Processed message: 2
//        return result;
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @GetMapping("/hello")
//    public String hello() {
//        // 发送消息
//        kafkaTemplate.send("inputTopic", "1");
//        return "hello";
//    }
//}
