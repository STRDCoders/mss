package strdcoders.mss.schedule

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import strdcoders.mss.repository.PartitionMonitor
import strdcoders.mss.repository.PartitionMonitorRepository
import strdcoders.mss.utils.DbUtils
import strdcoders.mss.utils.SpringBootTestcontainersMssTest

@SpringBootTestcontainersMssTest
class StorageTriggerSchedulerTest {

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
        partitionMonitorRepository.save(PartitionMonitor(file = "/", thresholdPercentage = 50))
    }
}
