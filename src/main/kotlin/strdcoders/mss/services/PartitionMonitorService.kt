package strdcoders.mss.services

import org.springframework.stereotype.Service
import strdcoders.mss.dto.PartitionMonitorDto
import strdcoders.mss.repository.PartitionMonitorRepository
import java.io.File

@Service
class PartitionMonitorService(
    private val partitionMonitorRepository: PartitionMonitorRepository,
) {
    fun getAllPartitions(): List<PartitionMonitorDto> {
        return partitionMonitorRepository.findAll().map { PartitionMonitorDto(File(it.file), it.thresholdPercentage) }
    }
}
