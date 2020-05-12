package com.example

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Delivery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.atomic.AtomicLong


@RestController
class Controller {



    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
        Greeting(counter.incrementAndGet(), "Hello, $name")

    fun auxFunction(str: String, num: Int):String{
        return "auxFunction: String=$str, Int=$num"
    }
    @GetMapping("/aux")
    fun echo(@RequestParam(value = "str") str: String, @RequestParam(value = "num") num: Int): String{
        return "Response aux: "+ auxFunction(str,num)
    }

    @GetMapping("/sendMessage")
    fun sendMessage(@RequestParam(value = "message") message: String,
                    @RequestParam(value = "queueHost") queueHost: String,
                    @RequestParam(value = "queuePort") queuePort: Int,
                    @RequestParam(value = "queueName") requestQueueName: String):String{
        //val queueHost = "localhost"
        //val queuePort = 5672
        //val requestQueueName = "Hello"
        //val message = message

        // connection to queue
        println("Connecting to queue: $requestQueueName@$queueHost:$queuePort")
        val factory = ConnectionFactory()
        factory.host = queueHost
        factory.port = queuePort
        factory.username = "teste"
        factory.password = "teste"
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        // set properties for server response
        val corrId = UUID.randomUUID().toString();
        val replyQueueName: String = channel.queueDeclare().getQueue()
        val props = AMQP.BasicProperties.Builder()
            .correlationId(corrId)
            .replyTo(replyQueueName)
            .build()

        // send message to queue
        println("$corrId: Sending \"$message\"")
        channel.basicPublish("", requestQueueName, props, message.toByteArray(charset("UTF-8")))
        println("$corrId: [x] Sent '$message'")

        println("$corrId: Waiting for response...")

        // listen to queue until a response message arrives
        val response: BlockingQueue<String> = ArrayBlockingQueue(1)

        val ctag = channel.basicConsume(replyQueueName, true,
            { consumerTag: String?, delivery: Delivery ->
                if (delivery.properties.correlationId == corrId) {
                    response.offer(String(delivery.body))
                }
            }
        ) { consumerTag: String? -> }

        val result = response.take()
        println("$corrId: Received message: $result")

        channel.basicCancel(ctag)

        println("Closing connection!")
        channel.close()
        connection.close()

        return result
    }

    @GetMapping("/receiveResponse")
    fun sendMessage(@RequestParam(value = "message") message: String){
        println("Received this message: $message")
    }
/*
    @GetMapping("/getMessages")
    fun getMessages(){
        val queueHost = "localhost"
        val queuePort = 5672
        val queueName = "Hello"
        val message = "Hello World!"

        // connection to queue
        println("Connecting to queue: $queueName@$queueHost:$queuePort")
        val factory = ConnectionFactory()
        factory.host = queueHost
        factory.port = queuePort
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        // send message to queue
        println("Getting all messages")
        channel.basicConsume(queue: queueName)
        println(" [x] Sent '$message'")

        channel.close()
        connection.close()
    }

 */
}