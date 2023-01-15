package strdcoders.mss.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@ConditionalOnProperty(name = ["application.scheduling.enabled"], havingValue = "true", matchIfMissing = true)
@EnableScheduling
class SchedulingConfig
