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
            println("java -jar QueuesProject.jar --spring.profiles.active=hello-world,receiver")
            println("java -jar QueuesProject.jar --spring.profiles.active=hello-world,sender")
            println("java -jar QueuesProject.jar --spring.profiles.active=work-queues,receiver")
            println("java -jar QueuesProject.jar --spring.profiles.active=work-queues,sender")
            println("java -jar QueuesProject.jar --spring.profiles.active=pub-sub,receiver")
            println("java -jar QueuesProject.jar --spring.profiles.active=pub-sub,sender")
            println("java -jar QueuesProject.jar --spring.profiles.active=routing,receiver")
            println("java -jar QueuesProject.jar --spring.profiles.active=routing,sender")
            println("java -jar QueuesProject.jar --spring.profiles.active=topics,receiver")
            println("java -jar QueuesProject.jar --spring.profiles.active=topics,sender")
            println("java -jar QueuesProject.jar --spring.profiles.active=rpc,client")
            println("java -jar QueuesProject.jar --spring.profiles.active=rpc,server")
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
