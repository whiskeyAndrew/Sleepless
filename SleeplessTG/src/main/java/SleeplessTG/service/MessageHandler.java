package SleeplessTG.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final KafkaProducer kafkaProducer;
    private final static String CHANNEL_MESSAGE_TYPE = "Channel";

    public void handleUpdate(Update update){
        if(update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            if(text.startsWith("Server")){
                handleServerMessage(text);
            }
            else if(text.startsWith(CHANNEL_MESSAGE_TYPE)){
                handleChangeChannelMessage(text);
            }
//            kafkaProducer.sendChangeChannelMessage(update.getMessage().getText());
        }
    }

    public void handleServerMessage(String message){

    }

    public void handleChangeChannelMessage(String message){
        message = message.replace(CHANNEL_MESSAGE_TYPE + " ","");
        kafkaProducer.sendChangeChannelMessage(message);
    }


}
