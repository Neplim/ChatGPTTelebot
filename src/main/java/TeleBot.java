
import Dto.ConfigDto;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static Helpers.ConfigHelper.*;


public class TeleBot extends TelegramLongPollingBot  {

    ConfigDto config = getConfig();
    public TeleBot() throws IOException {
    }

    @Override
    public void onUpdateReceived(Update update) {
        Duration timeout = Duration.ofMinutes(10);
        if (update.getMessage().getText().startsWith("@ChatLGPTbot")){
            if (config.getData().userList.stream().anyMatch(x->x.telegramId == update.getMessage().getFrom().getId()) && config.getData().chatList.stream().anyMatch(x->x.chatId == update.getMessage().getChatId())){
                if (update.getMessage().getText().startsWith("@ChatLGPTbot create")) {
                    OpenAiService GPT = new OpenAiService(config.data.openAIToken, timeout);
                    String customerMessage = update.getMessage().getText();
                    CreateImageRequest request = CreateImageRequest.builder()
                            .prompt(customerMessage)
                            .build();
                    SendMessage message = new SendMessage();
                    System.out.println("\nImage is located at:\n");
                    message.setChatId(update.getMessage().getChatId().toString());
                    message.setText("Image is located at:" + "\n" + GPT.createImage(request).getData().get(0).getUrl());
                    try {
                        execute(message); // Call method to send the message
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else


                    if (update.hasMessage() && update.getMessage().hasText()) {
                        OpenAiService GPT = new OpenAiService(config.data.openAIToken, timeout);
                        String customerMessage = update.getMessage().getText();
                        CompletionRequest completionRequest = CompletionRequest.builder()
                                .model("text-davinci-003")
                                .prompt(customerMessage)
                                .echo(true)
                                .user("testing")
                                .maxTokens(2048)
                                .n(1)
                                .build();
                        CompletionResult result = GPT.createCompletion(completionRequest);
                        String usage = result.getUsage().toString();
                        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
                        double cost = (result.getUsage().getTotalTokens() * 0.02) / 1000;
                        message.setChatId(update.getMessage().getChatId().toString());
                        message.setText(result.getChoices().get(0).getText() + "\n\n" + "Cost: " + cost + "$");
                        try {
                            execute(message); // Call method to send the message
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }

            }
            else {
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText(update.getMessage().getFrom().getFirstName() + " You are denied access or access from this chat is denied.");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.data.botname;
    }
    @Override
    public String getBotToken() {
        return config.data.telegramToken;
    }

}

