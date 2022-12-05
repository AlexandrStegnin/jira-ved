package com.stegnin.jiraved.service;

import com.stegnin.jiraved.utils.Emoji;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Alexandr Stegnin
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BotButtonManager {

    public void addRequestContactButton(SendMessage message) {
        // Создаем список строк клавиатуры
        var keyboard = new ArrayList<KeyboardRow>();

        // Добавляем кнопки в первую строчку клавиатуры
        var requestContactButton = KeyboardButton.builder()
                .text("Поделиться номером %s".formatted(Emoji.PHONE))
                .requestContact(true)
                .build();

        var firstKeyboardRow = new KeyboardRow(Collections.singletonList(requestContactButton));

        // Добавляем строчку клавиатуры в список
        keyboard.add(firstKeyboardRow);

        var replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();

        message.setReplyMarkup(replyKeyboardMarkup);

        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

}
