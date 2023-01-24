package com.pullout.objects

import com.pullout.app.utils.DateTimeUtil
import java.time.LocalDateTime
import java.util.Objects
import java.util.UUID

/**
 * Event Base
 */
abstract class Event {
    private val eventId: UUID = UUID.randomUUID()
    private val createdAt: LocalDateTime = DateTimeUtil.kstDateTimeNow()

    fun getEventId(): UUID = eventId
    fun getCreatedAt() = createdAt

    override fun equals(other: Any?): Boolean {
        return eventId == (other as Event).eventId
    }

    override fun hashCode() = Objects.hashCode(eventId)
}