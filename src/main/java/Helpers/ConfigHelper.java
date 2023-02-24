package Helpers;

import Object.ConfigObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import static Common.Constant.*;

public class ConfigHelper {

    public static ConfigObject getConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(CONFIG_PATH), ConfigObject.class);

    }

}
