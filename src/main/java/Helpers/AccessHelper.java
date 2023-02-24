package Helpers;

import Common.Context;

public class AccessHelper {

    public static boolean getAccess(Context context) {

        boolean result = false;

        if (context.getBotConfig().userList.stream().anyMatch(x -> x.telegramId == context
            .getUpdateMessage()
            .getMessage()
            .getFrom()
            .getId())
            &&
            context.getBotConfig().chatList.stream().anyMatch(x -> x.chatId == context
            .getUpdateMessage()
            .getMessage()
            .getChatId())) {

            result = true;
        }
        return result;
    }
}
