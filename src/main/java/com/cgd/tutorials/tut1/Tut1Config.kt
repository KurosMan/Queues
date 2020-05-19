package com.cgd.tutorials.tut1

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile


@Profile("tut1", "hello-world")
@Configuration
class Tut1Config {
    @Bean
    fun hello(): Queue {
        return Queue("rpc_queue")
    }

    @Profile("receiver")
    @Bean
    fun receiver(): Tut1Receiver {
        return Tut1Receiver()
    }

    @Profile("sender")
    @Bean
    fun sender(): Tut1Sender {
        return Tut1Sender()
    }
}