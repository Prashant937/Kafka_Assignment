package com.knoldus.serializer_deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.user.User;
import org.apache.kafka.common.serialization.Serializer;

public class ObjectSerializer implements Serializer<User> {
    @Override
    public byte[] serialize(String s, User user) {
        byte[] resultedByteArr = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            resultedByteArr = objectMapper.writeValueAsString(user).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultedByteArr;
    }
}

