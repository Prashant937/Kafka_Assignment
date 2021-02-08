package com.knoldus.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.user.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.knoldus.serializer_deserializer.ObjectDeserializer");
        properties.put("group.id", "User-Group");

        KafkaConsumer<String, User> kafkaConsumer = new KafkaConsumer<>(properties);
        List topics = new ArrayList();
        topics.add("User-Topic");
        kafkaConsumer.subscribe(topics);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            while (true) {
                FileWriter fileWriter = new FileWriter("consumed.txt", true);
                ConsumerRecords<String, User> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(60));
                for (ConsumerRecord<String, User> consumerRecord : consumerRecords) {
                    System.out.println(String.format("Topic: %s, Partition: %d, Value: %s", consumerRecord.topic(), consumerRecord.partition(), consumerRecord.value().toString()));
                    fileWriter.write(consumerRecord.value().toString() + "\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            kafkaConsumer.close();
        }
    }
}