package com.pullout.exceptions

import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import com.pullout.app.utils.DateTimeUtil
import mu.KLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import javax.validation.ConstraintViolationException


@Component
@ControllerAdvice
class ExceptionHandler {

    companion object : KLogging()

    /**
     * All Exception Handler
     */
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorResponse?>? {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorResponse(
                code = "INTERNAL_SERVER_ERROR",
                message = exception.message.toString()
            )
        )
    }

    /**
     * Validate Request Param
     */
    @ExceptionHandler
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    code = HttpStatus.BAD_REQUEST.toString(),
                    message = e.message.toString()
                )
            )
    }

    /**
     * Validate Response Body
     */
    @ExceptionHandler
    fun handleInvalidRequestBodyException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        logger.error { "occur Validation Exception : ${e.message}" }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                code = HttpStatus.BAD_REQUEST.toString(),
                message = e.bindingResult.allErrors.map { it.defaultMessage }[0].toString()
            )
        )

    }

    data class ErrorResponse(
        val code: String,
        val message: String,
        val occurExceptionTime: LocalDateTime = DateTimeUtil.kstDateTimeNow()
    )
}