package Common;

import Object.ConfigObject;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.time.Duration;
@Getter
@Setter
public class Context {

    private ConfigObject botConfig;
    private Update updateMessage;

}
