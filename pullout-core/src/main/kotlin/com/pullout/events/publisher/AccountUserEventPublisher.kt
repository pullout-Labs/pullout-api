package com.pullout.events.publisher

import com.pullout.enums.EventType
import com.pullout.events.dto.CreatedUserEvent
import mu.KLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

/**
 * user Event Publisher
 */
@Component
class AccountUserEventPublisher(
    val applicationEventMulticaster: ApplicationEventPublisher
) {
    companion object : KLogging()

    fun pushCreatedUserEvent(userId: String, type: EventType) {
        logger.info { "userCreated : $userId" }

        applicationEventMulticaster.publishEvent(
            CreatedUserEvent(
                userId = userId,
                eventType = type
            )

        )
    }

}