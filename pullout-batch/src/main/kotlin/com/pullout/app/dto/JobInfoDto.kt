package com.pullout.app.dto

/**
 * Job 정보
 */
data class JobInfoDto(
    val jobName: String,
    val groupName: String? = null,
    val scheduleTime : String? = null
)
