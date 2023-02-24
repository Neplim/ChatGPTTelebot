package Common;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import static Common.Constant.*;

import java.time.Duration;

import static Helpers.AccessHelper.*;

public class ChatInteraction {

    public static SendMessage responseCustomer(Context context) {

        SendMessage message = new SendMessage();

        if (getAccess(context)) {
            OpenAiService GPT = new OpenAiService(context.getBotConfig().openAIToken, Duration.ofMinutes(context.getBotConfig().gptResponseTimeout));
            String customerMessage = context.getUpdateMessage().getMessage().getText();
            CompletionRequest completionRequest = CompletionRequest.builder()
                    .model(context.getBotConfig().gptModel)
                    .prompt(customerMessage)
                    .echo(true)
                    .maxTokens(context.getBotConfig().maxToken)
                    .n(1)
                    .build();
            CompletionResult result = GPT.createCompletion(completionRequest);
            double cost = (result.getUsage().getTotalTokens() * 0.02) / 1000;
            message.setChatId(context.getUpdateMessage().getMessage().getChatId().toString());
            message.setText(result.getChoices().get(0).getText() + "\n\n" + "Cost: " + cost + "$");
        }


        else {

            message.setChatId(context.getUpdateMessage().getMessage().getChatId().toString());
            message.setText(context.getUpdateMessage().getMessage().getFrom().getFirstName() + ACCESS_DENIED);

        }

        return message;
    }
}
