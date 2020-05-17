package com.cgd.tutorials

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.context.ConfigurableApplicationContext

class RabbitAmqpTutorialsRunner : CommandLineRunner {
    @Value("\${tutorial.client.duration:0}")
    private val duration = 0

    @Autowired
    private val ctx: ConfigurableApplicationContext? = null

    @Throws(Exception::class)
    override fun run(vararg arg0: String) {
        println("Ready ... running for " + duration + "ms")
        Thread.sleep(duration.toLong())
        ctx!!.close()
    }
}