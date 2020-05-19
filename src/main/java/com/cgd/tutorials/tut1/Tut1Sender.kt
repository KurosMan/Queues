package com.cgd.tutorials.tut1

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import kotlin.math.roundToInt

// Sender

class Tut1Sender {
    @Autowired
    private val template: RabbitTemplate? = null

    @Autowired
    @Qualifier("hello")
    private val queue: Queue? = null

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    fun send() {
        val message = "Hello World! " + (Math.random()*1000).roundToInt()
        template!!.convertAndSend(queue!!.name, message)
        println(" [x] Sent '$message'")
    }
}
