package com.example.demo

import com.example.demo.excertion.ValidException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [ValidException::class])
    protected fun handValidException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val bodyOfResponse = ex.message
        return handleExceptionInternal(
            ex, bodyOfResponse,
            HttpHeaders(), HttpStatus.BAD_REQUEST, request
        )
    }

    @ExceptionHandler(value = [JdbcSQLIntegrityConstraintViolationException::class])
    protected fun handValid(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val bodyOfResponse = "The provided phone number or email is already exist"
        return handleExceptionInternal(
            ex, bodyOfResponse,
            HttpHeaders(), HttpStatus.BAD_REQUEST, request
        )
    }
}