package strdcoders.mss.schedule

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import strdcoders.mss.services.PartitionMonitorService
import java.io.File
import java.util.concurrent.TimeUnit

@Service
class StorageTriggerScheduler(private val partitionMonitorService: PartitionMonitorService) {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        const val convertToGb = 1024 * 1024 * 1024
    }

    @Scheduled(initialDelay = 2, fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    fun storageSchedule() {
        partitionMonitorService.getAllPartitions().forEach {
            log.info("Starting storage check schedule check")
            if (isAboveThreshold(it.file, it.thresholdPercentage)) {
                log.info("OVERLOAD")
            }
        }
    }

    private fun isAboveThreshold(partition: File, thresholdPercentage: Number): Boolean {
        val threshold = (partition.totalSpace * ((100 - thresholdPercentage.toDouble()) / 100)).toLong()
        log.info("name: ${partition.name}. Total space: ${partition.totalSpace} Free space: ${partition.usableSpace / (convertToGb)}, threshold: $threshold, usable: ${partition.usableSpace}")
        return partition.usableSpace < threshold
    }
}
