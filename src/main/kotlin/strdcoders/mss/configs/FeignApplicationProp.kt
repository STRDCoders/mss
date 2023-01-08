package strdcoders.mss.configs

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "feign.client.config.default")
class FeignApplicationProp {
    val defaultQueryParameters: DefaultQueryParameters = DefaultQueryParameters()

    class DefaultQueryParameters {
        lateinit var apiKey: String
    }
}
