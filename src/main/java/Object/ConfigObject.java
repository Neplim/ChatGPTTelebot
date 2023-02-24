package Object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Duration;
import java.util.List;

@Getter
@Setter
public class ConfigObject {

        @JsonProperty("Botname")
        public String botname;
        @JsonProperty("TelegramToken")
        public String telegramToken;
        @JsonProperty("OpenAIToken")
        public String openAIToken;
        @JsonProperty("UserList")
        public List<UserList> userList;
        @JsonProperty("ChatList")
        public List<ChatList> chatList;
        @JsonProperty("GPTModel")
        public String gptModel;
        @JsonProperty("MaxToken")
        public int maxToken;
        @JsonProperty("BotActivateMessage")
        public String botActivateMessage;
        @JsonProperty("GPTResponseTimeout")
        public int gptResponseTimeout;


    public static class UserList{
        @JsonProperty("TelegramId")
        public long telegramId;
    }

    public static class ChatList{
        @JsonProperty("ChatId")
        public long chatId;

    }
}
