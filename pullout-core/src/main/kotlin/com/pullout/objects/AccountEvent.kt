package com.pullout.objects

import com.pullout.enums.EventType

/**
 * Account Event
 */
abstract class AccountEvent(
    val eventType: EventType
) : Event() {

}



