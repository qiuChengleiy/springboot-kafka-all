package com.kafka.study.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.BatchMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author qcl
 * @Description
 * @Date 10:35 AM 3/24/2023
 */
@Slf4j
@Component
public class MyMessageListener implements BatchMessageListener<String, String> {

    @Override
    public void onMessage(List<ConsumerRecord<String, String>> consumerRecords) {
        for (ConsumerRecord<String, String> record : consumerRecords) {
            String message = record.value();
            // 处理消息
            log.info("onMessage: {}", message);
        }
    }

    @Override
    public void onMessage(List<ConsumerRecord<String, String>> records, Acknowledgment acknowledgment) {
        for (ConsumerRecord<String, String> record : records) {
            String message = record.value();
            // 处理消息
            log.info("onMessage: {}", message);
        }
        acknowledgment.acknowledge();
    }
}

