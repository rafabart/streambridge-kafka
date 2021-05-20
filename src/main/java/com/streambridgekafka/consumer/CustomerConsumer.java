package com.streambridgekafka.consumer;

import com.streambridgekafka.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class CustomerConsumer {


    @Bean
    public Consumer<Customer> receiveAmqp() {
        return customer -> {
            log.info("CustomerConsumer.receiveAmqp={}", customer);
        };
    }
}
