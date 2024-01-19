package service.carsharing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.carsharing.telegram.TelegramBotService;

@Configuration
public class TelegramBotConfig {
    private final TelegramBotService telegramBotService;

    public TelegramBotConfig(TelegramBotService telegramBotService) {
        this.telegramBotService = telegramBotService;
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBotService);
            return telegramBotsApi;
        } catch (TelegramApiException e) {
            throw new RuntimeException("Can't create Telegram bot API", e);
        }
    }
}
