package com.study.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.sound.midi.Soundbank;
import java.util.Date;

@Slf4j
@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send(){
        String context = "hello" + new Date();
        log.error("Sender:" + context);
        this.amqpTemplate.convertAndSend("hello",context);
    }
}
