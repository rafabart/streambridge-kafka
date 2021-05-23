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
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.ACCEPTED;

@Slf4j
@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final StreamBridge streamBridge;


    /*
    * A documentacao indica usar a message key da mensagem com serialização Bytes.
    * .getBytes(StandardCharsets.UTF_8)
    * */
    @PostMapping("/bytesKey")
    @ResponseStatus(ACCEPTED)
    public Boolean sendBytesKey(@RequestBody final Customer customer) {

        final Message<Customer> message = MessageBuilder
                .withPayload(customer)
                .setHeader(KafkaHeaders.MESSAGE_KEY, customer.getId().getBytes(StandardCharsets.UTF_8))
                .build();


        log.info("Message<Customer> = {}", message);
        return streamBridge.send("producer-customer-topic-bytes", message);
    }


    /*
    * Message key serializada como String
    * */
    @PostMapping("/stringKey")
    @ResponseStatus(ACCEPTED)
    public Boolean sendStringKey(@RequestBody final Customer customer) {

        final Message<Customer> message = MessageBuilder
                .withPayload(customer)
                .setHeader(KafkaHeaders.MESSAGE_KEY, customer.getId())
                .build();


        log.info("Message<Customer> = {}", message);
        return streamBridge.send("producer-customer-topic", message);
    }


    /*
     * Usando padrão Avro na serialização da mensagem
     * */
    @PostMapping("/avro")
    @ResponseStatus(ACCEPTED)
    public Boolean sendAvro(@RequestBody final Customer customer) {

        //Objeto avro gerado a partir do schema na pasta 'avro'
        final CustomerAvro customerAvro = CustomerAvro.newBuilder()
                .setId(customer.getId())
                .setName(customer.getName())
                .build();

        final Message<CustomerAvro> message = MessageBuilder
                .withPayload(customerAvro)
                .setHeader(KafkaHeaders.MESSAGE_KEY, customer.getId().getBytes(StandardCharsets.UTF_8))
                .build();


        log.info("Message<CustomerAvro> = {}", message);
        return streamBridge.send("producer-customer-topic-avro", message, MimeType.valueOf("application/+avro"));
    }


    /*
     * Usando RabbitMQ
     * */
    @PostMapping("/amqp")
    @ResponseStatus(ACCEPTED)
    public Boolean sendAmqp(@RequestBody final Customer customer) {

        final Message<Customer> message = MessageBuilder
                .withPayload(customer)
                .setHeader(KafkaHeaders.MESSAGE_KEY, customer.getId())
                .build();


        log.info("Message<Customer> = {}", message);
        return streamBridge.send("producer-customer-exchange-amqp", message);
    }
}
