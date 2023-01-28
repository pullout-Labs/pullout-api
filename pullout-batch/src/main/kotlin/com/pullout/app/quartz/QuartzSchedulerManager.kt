package com.pullout.app.quartz

import com.pullout.app.dto.JobInfoDto
import com.pullout.app.quartz.listeners.PulloutTriggerListener
import mu.KLogging
import org.quartz.Scheduler
import org.quartz.SchedulerException
import org.quartz.Trigger
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct


/**
 * Quartz Scheduler handler
 */
@Configuration
class QuartzSchedulerManager(
    private val scheduler: Scheduler
) {
    companion object : KLogging()

    @PostConstruct
    private fun initScheduler() {
        try {
            // set Listener
            scheduler.listenerManager
//                .addJobListener()

            // set Trigger
            scheduler.listenerManager
                .addTriggerListener(PulloutTriggerListener())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 활성화된 있는 작업 목록 조회
     */
    fun findAllActivatedJob(): List<JobInfoDto>? {
        val result: MutableList<JobInfoDto> = ArrayList()
        try {
            for (groupName in scheduler.jobGroupNames) {
                logger.info("groupName : $groupName")
                for (jobKey in scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    val trigger: List<Trigger> = scheduler.getTriggersOfJob(jobKey) as List<Trigger>
                    result.add(
                        JobInfoDto(
                            jobName = jobKey.name,
                            groupName = jobKey.group,
                            scheduleTime = trigger[0].startTime.toString()
                        )
                    )
                }
            }
        } catch (e: SchedulerException) {
            e.printStackTrace()
        }
        return result
    }


}