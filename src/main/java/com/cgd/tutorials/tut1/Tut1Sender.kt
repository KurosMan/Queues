package com.cgd.tutorials.tut1

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled

// Sender

class Tut1Sender {
    @Autowired
    private val template: RabbitTemplate? = null

    @Autowired
    private val queue: Queue? = null

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    fun send() {
        val message = "Hello World!"
        template!!.convertAndSend(queue!!.name, message)
        println(" [x] Sent '$message'")
    }
}
