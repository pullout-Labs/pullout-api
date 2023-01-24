package com.pullout.events.listener

import com.pullout.events.dto.CreatedUserEvent
import mu.KLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * AccountUserEvent Listener
 */
@Component
class AccountUserEventListener {
    companion object : KLogging()


    @EventListener
    fun loggingUserCreated(createdUserEvent: CreatedUserEvent) {
        logger.info { "eventId : ${createdUserEvent.getEventId()}" }
        logger.info { "user created with id : ${createdUserEvent.userId}" }
        logger.info { "created At : ${createdUserEvent.getCreatedAt()}" }
    }
}