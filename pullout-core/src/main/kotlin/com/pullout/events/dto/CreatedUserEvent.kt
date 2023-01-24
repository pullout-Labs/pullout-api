package com.pullout.events.dto

import com.pullout.enums.EventType
import com.pullout.objects.AccountEvent

/**
 * 사용자 생성 Event
 */
class CreatedUserEvent(
    val userId: String,
    eventType: EventType
) : AccountEvent(eventType) {
}