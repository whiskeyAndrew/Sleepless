package SleeplessTG.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

    private static final String CHANGE_CHANNEL_TOPIC = "CHANGE_CHANNEL_TOPIC";

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendChangeChannelMessage(String message) {
        message = message.trim();
        log.info("Sending change channel message: {}", message);
        kafkaTemplate.send(CHANGE_CHANNEL_TOPIC, message);
    }
}
