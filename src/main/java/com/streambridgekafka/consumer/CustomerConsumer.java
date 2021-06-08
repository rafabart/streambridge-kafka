package com.streambridgekafka.consumer;

import com.example.CustomerAvro;
import com.streambridgekafka.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class CustomerConsumer {


    @Bean
    public Consumer<Customer> receiveAmqp() {
        return customer -> {
            log.info("CustomerConsumer.receiveAmqp={}", customer);
            if (customer.getId().equals("dlq")) throw new RuntimeException("DLQ Simulation");
        };
    }


    @Bean
    public Consumer<Customer> receiveKafka() {
        return customer -> {
            log.info("CustomerConsumer.receiveKafka={}", customer);
        };
    }


    @Bean
    public Consumer<CustomerAvro> receiveKafkaAvro() {
        return customerAvro -> {
            log.info("Received Type={}", customerAvro.getClass().getCanonicalName());
            log.info("CustomerConsumer.receiveKafkaAvro={}", customerAvro);
        };
    }
}