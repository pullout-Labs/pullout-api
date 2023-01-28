package com.pullout.app.quartz.jobs

import com.pullout.app.domains.repositories.AccountUserRepository
import mu.KLogging
import org.quartz.DisallowConcurrentExecution
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.PersistJobDataAfterExecution
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 주차 알림 전송 Job
 */
@Component
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
class ParkingAlarmJob : Job {
    companion object : KLogging()

    override fun execute(context: JobExecutionContext?) {
        logger.info { "todo Something in parkingAlarm Job" }
    }
}