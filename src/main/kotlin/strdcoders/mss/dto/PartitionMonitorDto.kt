package strdcoders.mss.dto

import java.io.File
import java.io.Serializable

/**
 * A DTO for the {@link strdcoders.mss.repository.PartitionMonitor} entity
 */
data class PartitionMonitorDto(val file: File, val thresholdPercentage: Number) : Serializable
