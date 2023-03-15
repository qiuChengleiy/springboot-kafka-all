package com.kafka.study.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author qcl
 * @Description 错误处理器
 * @Date 10:52 AM 3/15/2023
 */
@Slf4j
@Component
public class MyErrorHandler implements KafkaListenerErrorHandler {
    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
        // 处理异常，例如打印错误日志、发送错误消息等,自定义逻辑处理
        log.error("Error occurred while processing message:  {}", e.getMessage());
        return null;
    }
}
