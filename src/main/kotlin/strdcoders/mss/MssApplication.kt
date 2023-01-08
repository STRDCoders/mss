package strdcoders.mss

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling
import strdcoders.mss.configs.FeignApplicationProp

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableConfigurationProperties(FeignApplicationProp::class)
class MssApplication

fun main(args: Array<String>) {
    runApplication<MssApplication>(*args)
}
