package ch.brontofundus.service.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@SpringBootApplication
@EnableScheduling
class ExampleServiceApplication {

    @Bean
    fun taskScheduler(): TaskScheduler {
        val threadPoolTaskScheduler = ThreadPoolTaskScheduler()

        threadPoolTaskScheduler.poolSize = 5
        threadPoolTaskScheduler.initialize()

        return threadPoolTaskScheduler
    }
}

fun main(args: Array<String>) {
    runApplication<ExampleServiceApplication>(*args)
}
