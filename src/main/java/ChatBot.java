
import Common.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.io.IOException;
import static Helpers.ConfigHelper.*;
import static Common.Constant.*;

public class ChatBot {

    public static void main(String[] args) throws IOException {

        Context context = new Context();
        context.setBotConfig(getConfig());

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TeleBot(context));
            System.out.println(ON_LISTEN);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
