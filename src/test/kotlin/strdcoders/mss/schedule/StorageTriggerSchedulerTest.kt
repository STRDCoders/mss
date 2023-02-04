package strdcoders.mss.schedule

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.mock.mockito.MockBean
import strdcoders.mss.dto.PartitionMonitorDto
import strdcoders.mss.repository.PartitionMonitor
import strdcoders.mss.repository.PartitionMonitorRepository
import strdcoders.mss.services.RuleFlowRunner
import strdcoders.mss.utils.DbUtils
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
        val partitionMonitor = PartitionMonitor(file = "/", thresholdPercentage = 50)
        val partitionMonitorDto = PartitionMonitorDto(File(partitionMonitor.file), partitionMonitor.thresholdPercentage)
        partitionMonitorRepository.save(partitionMonitor)
        Mockito.`when`(storageUtils.isFileAboveLimit(partitionMonitorDto.file, partitionMonitorDto.thresholdPercentage))
            .thenReturn(true)

        storageTriggerScheduler.storageSchedule()

        verify(storageUtils, Mockito.times(1)).isFileAboveLimit(
            partitionMonitorDto.file,
            partitionMonitorDto.thresholdPercentage,
        )
        verify(ruleFlowRunner, Mockito.times(1)).runRules(partitionMonitorDto)
    }
}
