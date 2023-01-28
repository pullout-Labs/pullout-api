package com.pullout.app.quartz.listeners

import com.pullout.app.utils.DateTimeUtil
import mu.KLogging
import org.quartz.JobExecutionContext
import org.quartz.Trigger
import org.quartz.TriggerListener
import kotlin.math.log

class PulloutTriggerListener : TriggerListener {
    companion object : KLogging()

    override fun getName(): String {
        logger.info { "PulloutTriggerListener Start At : ${DateTimeUtil.kstDateTimeNow()}" }

        return "PulloutTriggerListener"
    }

    /**
     * Trigger 실패
     */
    override fun triggerFired(trigger: Trigger?, context: JobExecutionContext?) {
        logger.info { "TriggerFired At : ${trigger?.startTime} :: with jobKey : ${trigger?.jobKey}" }
    }

    /**
     * Hearth Check
     */
    override fun vetoJobExecution(trigger: Trigger?, context: JobExecutionContext?): Boolean {
        logger.info { "check Trigger health" }

        return false
    }

    /**
     * Trigger 실행 안 됨
     */
    override fun triggerMisfired(trigger: Trigger?) {
        logger.info { "Trigger Misfired" }
        logger.info { "triggerMisfired at ${trigger?.startTime} :: jobKey : ${trigger?.jobKey}" }
    }

    /**
     * Trigger 종료
     */
    override fun triggerComplete(
        trigger: Trigger?,
        context: JobExecutionContext?,
        triggerInstructionCode: Trigger.CompletedExecutionInstruction?
    ) {
        logger.info { "Trigger Complete" }
        logger.info { "triggerMisfired at ${trigger?.startTime} :: jobKey : ${trigger?.jobKey}" }
    }

}