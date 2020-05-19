package com.cgd.tutorials

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class RabbitAmqpTutorialsApplication {
    @Profile("usage_message")
    @Bean
    fun usage(): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            println("This app uses Spring Profiles to control its behavior.\n")
            println("Options are: ")
            println("java -jar QueuesProject.jar --spring.profiles.active=hello-world,receiver --server.port=8081")
            println("java -jar QueuesProject.jar --spring.profiles.active=hello-world,sender --server.port=8082")
            println("java -jar QueuesProject.jar --spring.profiles.active=work-queues,receiver --server.port=8081")
            println("java -jar QueuesProject.jar --spring.profiles.active=work-queues,sender --server.port=8082")
            println("java -jar QueuesProject.jar --spring.profiles.active=pub-sub,receiver --server.port=8081")
            println("java -jar QueuesProject.jar --spring.profiles.active=pub-sub,sender --server.port=8082")
            println("java -jar QueuesProject.jar --spring.profiles.active=routing,receiver --server.port=8081")
            println("java -jar QueuesProject.jar --spring.profiles.active=routing,sender --server.port=8082")
            println("java -jar QueuesProject.jar --spring.profiles.active=topics,receiver --server.port=8081")
            println("java -jar QueuesProject.jar --spring.profiles.active=topics,sender --server.port=8082")
            println("java -jar QueuesProject.jar --spring.profiles.active=rpc,client --server.port=8081")
            println("java -jar QueuesProject.jar --spring.profiles.active=rpc,server --server.port=8082")
        }
    }

    @Profile("!usage_message")
    @Bean
    fun tutorial(): CommandLineRunner {
        return RabbitAmqpTutorialsRunner()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(RabbitAmqpTutorialsApplication::class.java, *args)
        }
    }
}
