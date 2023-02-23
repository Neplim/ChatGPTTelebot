package Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ConfigDto {

    @JsonProperty("Data")
    public Data data;

    public static class Data{
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
    }

    public static class UserList{
        @JsonProperty("TelegramId")
        public long telegramId;
    }

    public static class ChatList{
        @JsonProperty("ChatId")
        public long chatId;



    }
}
