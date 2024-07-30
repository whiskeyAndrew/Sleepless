package SleeplessTG.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String CHANGE_CHANNEL_TOPIC = "CHANGE_CHANNEL_TOPIC";

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendChangeChannelMessage(String message) {
        kafkaTemplate.send(CHANGE_CHANNEL_TOPIC, message.trim());
    }
}
