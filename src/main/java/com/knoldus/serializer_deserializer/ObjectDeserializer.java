package com.knoldus.serializer_deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.user.User;
import org.apache.kafka.common.serialization.Deserializer;

public class ObjectDeserializer implements Deserializer<User> {
    @Override
    public User deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(bytes, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

