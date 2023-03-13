package com.kafka.study.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * @Author qcl
 * @Description
 * @Date 4:46 PM 3/13/2023
 */
@Configuration
public class KafkaConfig {
    @Bean
    public KafkaAdmin admin(KafkaProperties properties){
        KafkaAdmin admin = new KafkaAdmin(properties.buildAdminProperties());
        // 默认False,在Broker不可用时,如果你觉得Broker不可用影响正常业务需要显示的将这个值设置为True
        admin.setFatalIfBrokerNotAvailable(true);

        // setAutoCreate(false) : 默认值为True，也就是Kafka实例化后会自动创建已经实例化的NewTopic对象
        // initialize()：当setAutoCreate为false时，需要我们程序显示的调用admin的initialize()方法来初始化NewTopic对象

        return admin;
    }

    /**
     * 创建指定参数的 topic
     * @return
     */
    @Bean
    public NewTopic topic() {
        return new NewTopic("hello2", 0, (short) 0);
    }

    /**
     * 更新 topic
     * @return
     */
    @Bean
    public NewTopic topicUpdate() {
        return new NewTopic("hello2", 1, (short) 1);
    }
}
