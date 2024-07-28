package SleeplessTG.util;


import org.apache.commons.lang3.EnumUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;


public class SendMessageFactory {

    public SendMessage createSendMessage(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText() || message.hasLocation()) {
                handleIncomingMessage(message);
            }
        }
        return null;
    }

    private void handleIncomingMessage(Message message) {
        System.out.println(message.getText());
        if (EnumUtils.isValidEnum(StartPanelValues.class, message.getText())) {
            handleStartMessage(message);
        }
//        else if()
    }

    private void handleStartMessage(Message message) {
        StartPanelValues startValue = StartPanelValues.valueOf(message.getText());
    }
}
