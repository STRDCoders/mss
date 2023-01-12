package strdcoders.mss.schedule

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.File
import java.util.concurrent.TimeUnit

@Service
class StorageTriggerScheduler {
    companion object {
        const val convertToGb = 1024 * 1024 * 1024
    }

    @Scheduled(initialDelay = 2, fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    fun storageSchedule() {
        // should have a list of monitored partitions

        // for each get space
        val partition: File = File("/")
        println(partition.usableSpace)
        println(partition.usableSpace / (convertToGb))
    }
}
