package strdcoders.mss.utils

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.MockServerContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName
import java.util.concurrent.atomic.AtomicBoolean

class ContainersSetup {

    class MockServer : BeforeAllCallback {
        companion object {
            val started = AtomicBoolean(false)
            val mockServer: MockServerContainer =
                MockServerContainer(DockerImageName.parse("jamesdbloom/mockserver:mockserver-5.11.2"))
        }

        override fun beforeAll(context: ExtensionContext?) {
            if (started.compareAndSet(false, true)) {
                mockServer.start()
                System.setProperty("lsd.logo-grab.api.brand.base", mockServer.endpoint)
                System.setProperty("lsd.internal-gateway.base-url", mockServer.endpoint)
                System.setProperty("lsd.site-content.base-url", mockServer.endpoint)
                System.setProperty("lsd.risk-score.base-url", mockServer.endpoint)
            }
        }
    }

    class MysqlServer : BeforeAllCallback {
        companion object {
            val started = AtomicBoolean(false)
            val mysql = MySQLContainer("mysql:8.0.31")
        }

        override fun beforeAll(context: ExtensionContext?) {
            if (started.compareAndSet(false, true)) {
                mysql.start()
                System.setProperty(
                    "spring.datasource.url",
                    "jdbc:mysql://${mysql.host}:${mysql.firstMappedPort}/${mysql.databaseName}?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true",
                )
                System.setProperty(
                    "spring.datasource.username",
                    mysql.username,
                )
                System.setProperty(
                    "spring.datasource.password",
                    mysql.password,
                )
            }
        }
    }
}

@SpringBootTest
@ExtendWith(ContainersSetup.MockServer::class, ContainersSetup.MysqlServer::class)
@ActiveProfiles(profiles = ["test"])
annotation class SpringBootTestcontainersMssTest
