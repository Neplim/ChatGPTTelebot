package Helpers;

import Dto.ConfigDto;
import java.io.File;
import java.io.IOException;
import static Constant.Constant.*;
import static Helpers.MapperHelper.Mapper;

public class ConfigHelper {


    public static ConfigDto getConfig() throws IOException {

        ConfigDto config = Mapper().readValue(new File(CONFIG_PATH), ConfigDto.class);
        System.out.println();
        return config;

    }

}
