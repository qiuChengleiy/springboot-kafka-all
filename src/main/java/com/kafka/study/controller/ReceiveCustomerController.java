//package com.kafka.study.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
//import org.springframework.kafka.requestreply.RequestReplyFuture;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Author qcl
// * @Description 消费者应答
// * @Date 5:20 PM 3/13/2023
// */
//@Slf4j
//@RestController
//public class ReceiveCustomerController {
//
//    private static final String topic = "hello3";
//    private static final String topicCroup = "hello3Group";
//
//    @Bean
//    public ConcurrentMessageListenerContainer<String, String> repliesContainer(ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
//        ConcurrentMessageListenerContainer<String, String> repliesContainer = containerFactory.createContainer(topic + "_replies");
//        repliesContainer.getContainerProperties().setGroupId(topicCroup);
//        repliesContainer.setAutoStartup(false);
//        return repliesContainer;
//    }
//
//    @Bean
//    public ReplyingKafkaTemplate<String, String, String> replyingTemplate(ProducerFactory<String, String> producerFactory, ConcurrentMessageListenerContainer<String, String> repliesContainer) {
//        return new ReplyingKafkaTemplate(producerFactory, repliesContainer);
//    }
//
//    @Bean
//    public KafkaTemplate kafkaTemplate(ProducerFactory<String, String> producerFactory) {
//        return new KafkaTemplate(producerFactory);
//    }
//
//    @Autowired
//    private ReplyingKafkaTemplate kafkaReplyTemplate;
//
//    @GetMapping("/send/{msg}")
//    @Transactional(rollbackFor = RuntimeException.class)
//    public void sendMsg(@PathVariable String msg) throws Exception {
//        ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
//        RequestReplyFuture<String, String, String> replyFuture = kafkaReplyTemplate.sendAndReceive(record);
//        ConsumerRecord<String, String> consumerRecord = replyFuture.get();
//        log.info("customer reply >>>> {}: ", consumerRecord.value()); // customer reply >>>>listen： I do it >>> 1:
//    }
//
//    @KafkaListener(id = topicCroup, topics = topic)
//    @SendTo
//    public String listen(String msg) {
//        log.info("listen receive msg >>>  {}", msg); // listen1 receive msg >>>  1
//        return "listen: I do it >>> " +msg;
//    }
//}
