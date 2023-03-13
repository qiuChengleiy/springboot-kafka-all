//package com.kafka.study.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.admin.AdminClient;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
///**
// * @Author qcl
// * @Description
// * @Date 11:35 AM 3/10/2023
// */
//@Slf4j
//@RestController
//public class HelloController {
//
//    private static final String topic = "test";
//
//    @Autowired
//    private KafkaTemplate<Object, Object> kafkaTemplate;
//
//    // 接收消息
//    @KafkaListener(id = "helloGroup", topics = topic)
//    public void listen(String msg) {
//        log.info("hello receive value: {}" , msg);
//        // hello receive value: hello kafka
//    }
//
//    @GetMapping("/hello")
//    public String hello() {
//        // 发送消息
//        kafkaTemplate.send(topic, "hello kafka");
//        return "hello";
//    }
//
//    // 当topic不存在时 会默认创建一个topic
//    // num.partitions = 1 #默认Topic分区数
//    // num.replica.fetchers = 1 #默认副本数
//    @GetMapping("/hello1")
//    public String hello1() {
//        // 发送消息
//        kafkaTemplate.send("hello1", "hello1");
//        return "hello1";
//    }
//
//    // 接收消息
//    @KafkaListener(id = "hello1Group", topics = "hello1")
//    public void listen1(String msg) {
//        log.info("hello1 receive value: {}" , msg);
//        // hello1 receive value: hello1
//    }
//
//    /**
//     * AdminClient 创建
//     */
//    @Autowired
//    private KafkaProperties properties;
//
//    @GetMapping("/create/{topicName}")
//    public String createTopic(@PathVariable String topicName) {
//        AdminClient client = AdminClient.create(properties.buildAdminProperties());
//        if(client !=null){
//            try {
//                Collection<NewTopic> newTopics = new ArrayList<>(1);
//                newTopics.add(new NewTopic(topicName,1,(short) 1));
//                client.createTopics(newTopics);
//            }catch (Throwable e){
//                e.printStackTrace();
//            }finally {
//                client.close();
//            }
//        }
//        return topicName;
//    }
//
//    /**
//     * 获取通知结果
//     * @return
//     */
//    @GetMapping("/hello2")
//    public String hello2() {
//        // 发送消息 - 异步获取通知结果
//        kafkaTemplate.send("hello2", "async hello2").addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                log.error("fail >>>>{}", throwable.getMessage());
//            }
//
//            @Override
//            public void onSuccess(SendResult<Object, Object> objectObjectSendResult) {
//                log.info("async success >>> {}", objectObjectSendResult.getRecordMetadata().topic()); // async success >>> hello2
//            }
//        });
//
//        // 同步获取结果
//        ListenableFuture<SendResult<Object,Object>> future = kafkaTemplate.send("hello2","hello2");
//        try {
//            SendResult<Object,Object> result = future.get();
//            log.info("success >>> {}", result.getRecordMetadata().topic()); // success >>> hello2
//        }catch (Throwable e){
//            e.printStackTrace();
//        }
//
//        return "hello2";
//    }
//
//    /**
//     * kafka 事务
//     * 默认情况下，Spring-kafka自动生成的KafkaTemplate实例，是不具有事务消息发送能力的。需要使用如下配置激活事务特性。
//     * 事务激活后，所有的消息发送只能在发生事务的方法内执行了，不然就会抛一个没有事务交易的异常
//     * @return
//     */
//    @GetMapping("/hello3")
//    public String hello3() {
//        kafkaTemplate.executeInTransaction(t -> {
//            t.send("hello3","msg1");
//            if(true)
//                throw new RuntimeException("failed");
//            t.send("hello3","msg2");
//            return true;
//        });
//
//        return "hello3";
//    }
//
//    // 注解方式
//    @Transactional(rollbackFor = RuntimeException.class)
//    @GetMapping("/hello4")
//    public String hello4() {
//        kafkaTemplate.send("hello3","msg1");
//        if(true)
//            throw new RuntimeException("failed");
//        kafkaTemplate.send("hello3","msg2");
//        return "hello4";
//    }
//
//    // 接收消息
//    @KafkaListener(id = "hello3Group", topics = "hello3")
//    public void listen3(String msg) {
//        log.info("hello3 receive value: {}" , msg);
//    }
//}
