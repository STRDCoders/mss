package strdcoders.mss

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class MssApplication

fun main(args: Array<String>) {
    runApplication<MssApplication>(*args)
}
