package strdcoders.mss.schedule

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import strdcoders.mss.services.PartitionMonitorService
import strdcoders.mss.utils.StorageUtils
import java.util.concurrent.TimeUnit

@Service
class StorageTriggerScheduler(
    private val partitionMonitorService: PartitionMonitorService,
    private val storageUtils: StorageUtils,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Scheduled(initialDelay = 2, fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    fun storageSchedule() {
        partitionMonitorService.getAllPartitions().forEach {
            log.info("Starting storage check schedule check")
            if (storageUtils.isFileAboveLimit(it.file, it.thresholdPercentage)) {
                log.info("OVERLOAD")
            }
        }
    }
}
