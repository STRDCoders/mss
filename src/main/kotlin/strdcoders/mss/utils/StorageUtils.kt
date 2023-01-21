package strdcoders.mss.utils

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File

@Service
class StorageUtils {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        const val convertToGb = 1024 * 1024 * 1024
    }

    fun isFileAboveLimit(partition: File, usedStorageThresholdPercentage: Number): Boolean {
        val threshold = (partition.totalSpace * ((100 - usedStorageThresholdPercentage.toDouble()) / 100)).toLong()
        log.info("name: ${partition.name}. Total space: ${partition.totalSpace} Free space: ${partition.usableSpace / convertToGb}, threshold: $threshold, usable: ${partition.usableSpace}")
        return partition.usableSpace < threshold
    }
}
