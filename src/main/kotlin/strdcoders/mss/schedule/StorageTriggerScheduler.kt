package strdcoders.mss.schedule

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import strdcoders.mss.services.PartitionMonitorService
import strdcoders.mss.services.RuleFlowRunner
import strdcoders.mss.utils.StorageUtils
import java.util.concurrent.TimeUnit

@Service
class StorageTriggerScheduler(
    private val partitionMonitorService: PartitionMonitorService,
    private val storageUtils: StorageUtils,
    private val ruleFlowRunner: RuleFlowRunner,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Scheduled(initialDelay = 2, fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    fun storageSchedule() {
        log.info("Starting storage check schedule check")
        partitionMonitorService.getAllPartitions().forEach {
            log.debug("Checking partition: ${it.file.name} with a threshold of: ${it.thresholdPercentage}")
            if (storageUtils.isFileAboveLimit(it.file, it.thresholdPercentage)) {
                log.info("Partition '${it.file.name}' is above the limit: ${it.thresholdPercentage}% with free disk space of: ${it.file.usableSpace / StorageUtils.convertToGb}GB")
                ruleFlowRunner.runRules(it)
            }
        }
    }
}
