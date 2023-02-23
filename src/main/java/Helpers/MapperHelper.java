package Helpers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MapperHelper extends ObjectMapper{

    private static final ObjectMapper _Mapper = new ObjectMapper();


    public static ObjectMapper Mapper() {

        _Mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        _Mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        return _Mapper;
    }




}