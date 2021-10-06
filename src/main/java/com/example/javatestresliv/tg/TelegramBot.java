package com.example.javatestresliv.tg;

import com.example.javatestresliv.exception.BusinessException;
import com.example.javatestresliv.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingCommandBot {

    CityService cityService;

    @Autowired
    TelegramBot(CityService cityService) {
        this.cityService = cityService;
        register(new StartCommand("start", "start command"));
    }

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        String cityName = update.getMessage().getText().trim();
        String chatId = update.getMessage().getChatId().toString();
        setAnswer(chatId, cityName);
    }

    private void setAnswer(String chatId, String cityName) {
        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);
        setCityByName(answer, cityName);
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setCityByName(SendMessage sendMessage, String cityName) {
        try {
            sendMessage.setText(cityService.getByName(cityName).getMessage());
        } catch (BusinessException e) {
            sendMessage.setText(e.getMessage());
        }
    }
}
