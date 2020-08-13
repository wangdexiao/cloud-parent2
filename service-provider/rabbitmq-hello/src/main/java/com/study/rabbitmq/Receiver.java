package com.study.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "hello")
public class Receiver {


    @RabbitHandler
    public void process(String hello){
        log.error("Receiver:" + hello);
    }

}
