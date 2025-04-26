package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class JsonReader {
    public static Map<String, String> readContactDetails(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}