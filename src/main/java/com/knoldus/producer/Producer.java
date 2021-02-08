package com.knoldus.producer;

import com.knoldus.user.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class Producer {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.knoldus.serializer_deserializer.ObjectSerializer");

        KafkaProducer<String, User> kafkaProducer = new KafkaProducer<>(properties);

        try {
            for (int counter = 1; counter <= 10; counter++) {
                User user = new User(counter, "Prashant", (int) (30 + (Math.random() * 60)), "BTech");

                kafkaProducer.send(new ProducerRecord("User-Topic",String.valueOf(user.getId()),user));

                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            kafkaProducer.close();
        }
    }
}
