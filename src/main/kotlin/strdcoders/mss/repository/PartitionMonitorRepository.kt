package strdcoders.mss.repository

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import strdcoders.mss.dto.PartitionMonitorDto
import java.io.File

@Repository
interface PartitionMonitorRepository : JpaRepository<PartitionMonitor, String>

@Entity
@Table(name = "partition_monitor")
data class PartitionMonitor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @NotBlank
    @Column(unique = true)
    val file: String = "",

    @Column(name = "threshold_percentage", nullable = false)
    val thresholdPercentage: Short = 100,
)

fun PartitionMonitor.toDTO() = PartitionMonitorDto(File(file), thresholdPercentage)
