package com.bank.bootcoin.service;

import com.bank.bootcoin.entity.BootCoin;
import com.bank.bootcoin.events.BootCoinCreatedEvent;
import com.bank.bootcoin.events.Event;
import com.bank.bootcoin.events.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class BootCoinEventService {
    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${topic.credit.name:bootCointopic}")
    private String topicPublish;

    public void publish(BootCoin bootCoin) {
        BootCoinCreatedEvent created = new BootCoinCreatedEvent();
        created.setData(bootCoin);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());
        this.producer.send(topicPublish, created);
    }
}
