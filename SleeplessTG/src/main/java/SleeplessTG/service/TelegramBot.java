package SleeplessTG.service;

import SleeplessTG.util.SendMessageFactory;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TelegramBot implements LongPollingUpdateConsumer {

    private final TelegramClient telegramClient;
    private final MessageHandler messageHandler;

    //    public void consume(List<Update> list) {
//        for (Update update : list) {
//            if (update.hasMessage() && update.getMessage().hasText()) {
//                System.out.println(update.getMessage().getText());
//                SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), "HELLO WORLD");
//                try {
//                    telegramClient.execute(sendMessage);
//                } catch (TelegramApiException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }

    @Override
    public void consume(List<Update> list) {
        System.out.println("test");
        String chatId = list.get(0).getMessage().getChatId().toString();
        SendMessageFactory sendMessageFactory = new SendMessageFactory();
        for (Update update : list) {
            messageHandler.handleUpdate(update);
        }
//            SendMessage message = sendMessageFactory.createSendMessage(update);
//            update.getUpdateId();
//            if (update.hasMessage() && update.getMessage().hasText()) {
//                System.out.println(update.getMessage().getText());
//                // Create the keyboard (list of keyboard rows)
//                List<KeyboardRow> keyboard = new ArrayList<>();
//                // Create a keyboard row
//                KeyboardRow row = new KeyboardRow();
//                // Set each button, you can also use KeyboardButton objects if you need something else than text
//                row.add("Row 1 Button 1");
//                row.add("Row 1 Button 2");
//                row.add("Row 1 Button 3");
//                // Add the first row to the keyboard
//                keyboard.add(row);
//                // Create another keyboard row
//                row = new KeyboardRow();
//                // Set each button for the second line
//                row.add("Row 2 Button 1");
//                row.add("Row 2 Button 2");
//                row.add("Row 2 Button 3");
//                // Add the second row to the keyboard
//                keyboard.add(row);
//                // Set the keyboard to the markup
//
//                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(keyboard);
//                try {
//                    telegramClient.execute(SendMessage.builder().chatId(chatId).replyMarkup(keyboardMarkup).text("AAAAAA").build());
//                } catch (TelegramApiException e) {
//                    throw new RuntimeException(e);
//                }
//            }

    }
}
