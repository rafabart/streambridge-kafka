package com.streambridgekafka.controller;

import com.example.CustomerAvro;
import com.streambridgekafka.domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final StreamBridge streamBridge;


    @PostMapping("/bytesKey")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean sendBytesKey(@RequestBody final Customer customer) {

        final Message<Customer> message = MessageBuilder
                .withPayload(customer)
                .setHeader(KafkaHeaders.MESSAGE_KEY, customer.getId().getBytes(StandardCharsets.UTF_8))
                .build();


        log.info("Message<Customer> = {}", message);
        return streamBridge.send("customer-topic-binder-bytes", message);
    }


    @PostMapping("/stringKey")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean sendStringKey(@RequestBody final Customer customer) {

        final Message<Customer> message = MessageBuilder
                .withPayload(customer)
                .setHeader(KafkaHeaders.MESSAGE_KEY, customer.getId())
                .build();


        log.info("Message<Customer> = {}", message);
        return streamBridge.send("customer-topic-binder", message);
    }


    @PostMapping("/avro")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean sendAvro(@RequestBody final Customer customer) {

        final CustomerAvro customerAvro = CustomerAvro.newBuilder()
                .setId(customer.getId())
                .setName(customer.getName())
                .build();

        final Message<CustomerAvro> message = MessageBuilder
                .withPayload(customerAvro)
                .setHeader(KafkaHeaders.MESSAGE_KEY, customerAvro.getId())
                .build();


        log.info("Message<CustomerAvro> = {}", message);
        return streamBridge.send("customer-topic-binder-avro", message);
    }


    @PostMapping("/amqp")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean sendAmqp(@RequestBody final Customer customer) {

        final Message<Customer> message = MessageBuilder
                .withPayload(customer)
                .setHeader(KafkaHeaders.MESSAGE_KEY, customer.getId())
                .build();


        log.info("Message<Customer> = {}", message);
        return streamBridge.send("customer-topic-binder-amqp", message);
    }
}
