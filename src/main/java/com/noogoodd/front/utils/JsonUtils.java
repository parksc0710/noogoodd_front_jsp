package com.noogoodd.front.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static <T> T parseJsonToModel(String jsonString, Class<T> modelClass) throws IOException {
        return objectMapper.readValue(jsonString, modelClass);
    }

}
