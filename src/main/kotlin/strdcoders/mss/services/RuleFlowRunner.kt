package strdcoders.mss.services

import strdcoders.mss.dto.PartitionMonitorDto

interface RuleFlowRunner {
    fun runRules(partitionMonitorDto: PartitionMonitorDto)
}
