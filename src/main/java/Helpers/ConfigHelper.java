package Helpers;

import Object.ConfigObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ConfigHelper {

    public static ConfigObject getConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ConfigObject config = mapper.readValue(new File("./config.json"), ConfigObject.class);
        System.out.println();
        return config;

    }

}
