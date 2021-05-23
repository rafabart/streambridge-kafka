Projeto com objetivo de construir uma api que usa vários binders de integração assíncrona(Kafka e Rabbit) usando o StreamBridge.
Usa algumas opções diferentes de configurar os arquivos yml dando um leque de opções de escolha.

Usando:

* JDK 11
* Springboot 2.4.5
* Avro 1.10.0
* Apache kafka Streams
*	Spring kafka
* Spring cloud stream binder kafka
*	Spring cloud stream binder rabbit
* IntelliJ
* Rest
* Lombok
* Gradle
* Docker


Subindo ambiente docker com o kafka, rabbit, kafdrop e Schema Registry UI:
```
  docker-compose up -d
 ```

<p>Rest</p>
Usando o Postman para criar um novo customer e envia-los a bindings diversos:

URL: http://localhost:8080/customers

<p>URI: /bytesKey</p>
Verbo: POST
```
{
    "id":"1",
    "name":"Michael Jordan"
}
```

<p>URI: /stringKey</p>
Verbo: POST
```
{
    "id":"2",
    "name":"Michael Jordan"
}
<p>URI: /avro</p>
Verbo: POST
```
{
    "id":"3",
    "name":"Michael Jordan"
}
```

<p>URI: /amqp</p>
Verbo: POST
```
{
    "id":"4",
    "name":"Michael Jordan"
}
