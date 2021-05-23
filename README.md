Projeto com objetivo de construir uma api que usa vários binders de integração assíncrona(Kafka e Rabbit) usando o StreamBridge.
Usa algumas opções diferentes de configurar os arquivos yml dando um leque de opções de escolha.

**Usando:**

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
       
 ### Acesso as ferramentas:
    
 Kafdrop UI: http://localhost:19000/
    
 Schema Registry UI: http://localhost:8000/
     
 Rabbit MQ Management: http://localhost:15672/
    

### Rest:
Usando o Postman para criar um novo customer e envia-los a bindings diversos:

URL: http://localhost:8080/customers

URI: /bytesKey   
Verbo: POST
   
```
{
    "id":"1",
    "name":"Michael Jordan"
}
```
   
URI: /stringKey   
Verbo: POST
   
```
{
    "id":"2",
    "name":"Michael Jordan"
}
```
   
URI: /avro   
Verbo: POST
   
```
{
    "id":"3",
    "name":"Michael Jordan"
}
```

URI: /amqp   
Verbo: POST
   
```
{
    "id":"4",
    "name":"Michael Jordan"
}
```
