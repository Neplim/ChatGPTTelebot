package Common;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import static Common.ChatInteraction.*;


public class TeleBot extends TelegramLongPollingBot {
    private final Context context;

    public TeleBot(Context context) throws IOException {
        super(new DefaultBotOptions(), context.getBotConfig().telegramToken);
        this.context = context;
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().getText().startsWith(context.getBotConfig().botActivateMessage)) {
            context.setUpdateMessage(update);
            try {
                execute(responseCustomer(context));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public String getBotUsername() {
        return context.getBotConfig().botname;
    }
    @Override
    public String getBotToken() {return context.getBotConfig().telegramToken;
    }

}

