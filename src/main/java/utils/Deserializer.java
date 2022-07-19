package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;

public class Deserializer {

    public static <T> T deserializeResponse(Response response, Class T) {
        ObjectMapper mapper = new ObjectMapper();
        T responseDeserialized = null;
        try {
            responseDeserialized = (T) mapper.readValue(response.asString(), T);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return responseDeserialized;
    }

    public static <T> T jsonFileToModel(String fileName, Class<T> type) { //maybe rename method
        ObjectMapper objectMapper = new ObjectMapper();
        T model = null;
        try {
            model = objectMapper.readValue(new File("src/test/resources/testData/" + fileName), type);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return model;
    }
}
