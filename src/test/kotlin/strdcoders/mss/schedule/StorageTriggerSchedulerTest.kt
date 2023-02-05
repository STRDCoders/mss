package strdcoders.mss.schedule

import org.instancio.Instancio
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.mock.mockito.MockBean
import strdcoders.mss.dto.PartitionMonitorDto
import strdcoders.mss.repository.PartitionMonitor
import strdcoders.mss.repository.PartitionMonitorRepository
import strdcoders.mss.repository.toDTO
import strdcoders.mss.services.RuleFlowRunner
import strdcoders.mss.utils.DbUtils
import strdcoders.mss.utils.MockitoUtils
import strdcoders.mss.utils.SpringBootTestcontainersMssTest
import strdcoders.mss.utils.StorageUtils
import java.io.File

@AutoConfigureMockMvc
@SpringBootTestcontainersMssTest
class StorageTriggerSchedulerTest {

    @MockBean
    private lateinit var ruleFlowRunner: RuleFlowRunner

    @MockBean
    private lateinit var storageUtils: StorageUtils

    @Autowired
    private lateinit var storageTriggerScheduler: StorageTriggerScheduler

    @Autowired
    private lateinit var partitionMonitorRepository: PartitionMonitorRepository

    @Autowired
    private lateinit var dbUtils: DbUtils

    @BeforeEach
    fun init() {
        dbUtils.deleteAll()
    }

    @Test
    fun `when have partitions, should loop through them and decide for each one if should trigger score inspection`() {
        val partitionMonitor = Instancio.create(PartitionMonitor::class.java)
        val partitionMonitorDto = PartitionMonitorDto(File(partitionMonitor.file), partitionMonitor.thresholdPercentage)
        partitionMonitorRepository.save(partitionMonitor)
        `when`(storageUtils.isFileAboveLimit(partitionMonitorDto.file, partitionMonitorDto.thresholdPercentage))
            .thenReturn(true)

        storageTriggerScheduler.storageSchedule()

        verify(storageUtils, times(1)).isFileAboveLimit(
            partitionMonitorDto.file,
            partitionMonitorDto.thresholdPercentage,
        )
        verify(ruleFlowRunner, times(1)).runRules(partitionMonitorDto)
    }

    @Test
    fun `when have no partitions, should do nothing`() {
        storageTriggerScheduler.storageSchedule()
        verify(storageUtils, never()).isFileAboveLimit(MockitoUtils.anyNonNull(), MockitoUtils.anyNonNull())
        verify(ruleFlowRunner, never()).runRules(MockitoUtils.anyNonNull())
    }

    @Test
    fun `when have partitions with more available space then threshold, should do nothing`() {
        val partitionMonitor = Instancio.create(PartitionMonitor::class.java)
        val partitionMonitorDTO = partitionMonitor.toDTO()
        partitionMonitorRepository.save(partitionMonitor)

        `when`(storageUtils.isFileAboveLimit(MockitoUtils.anyNonNull(), MockitoUtils.anyNonNull()))
            .thenReturn(false)

        storageTriggerScheduler.storageSchedule()

        verify(storageUtils, times(1)).isFileAboveLimit(
            partitionMonitorDTO.file,
            partitionMonitorDTO.thresholdPercentage,
        )
        verify(ruleFlowRunner, never()).runRules(MockitoUtils.anyNonNull())
    }
}
