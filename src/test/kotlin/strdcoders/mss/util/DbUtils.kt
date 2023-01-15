package strdcoders.mss.util

import org.springframework.stereotype.Service
import strdcoders.mss.repository.PartitionMonitorRepository

@Service
class DbUtils(
    private val partitionMonitorRepository: PartitionMonitorRepository,
) {
    fun deleteAll() {
        partitionMonitorRepository.deleteAll()
    }
}
