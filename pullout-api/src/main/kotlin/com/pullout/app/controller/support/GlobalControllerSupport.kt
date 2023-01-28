package com.pullout.app.controller.support

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

/**
 * token handler
 */
open class GlobalControllerSupport {

    /**
     * get userName from Authentication
     * @return
     */
    fun getUserId(): String = getAuthentication().name ?: ""

    /**
     * get Authentication from token
     * @return
     */
    private fun getAuthentication(): Authentication = SecurityContextHolder.getContext().authentication
}