package me.edurevsky.blog.blogqualquer.exceptions

import me.edurevsky.blog.blogqualquer.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.persistence.EntityNotFoundException
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(e: Exception, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = e.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequest(e: MethodArgumentNotValidException, request: HttpServletRequest): ErrorView {
        val message = HashMap<String, String?>()
        e.bindingResult.fieldErrors.forEach { error -> message[error.field] = error.defaultMessage }

        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = message.toString(),
            path = request.servletPath
        )
    }

    @ExceptionHandler(ClosedPostException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleClosedPostException(e: ClosedPostException, request: HttpServletRequest): ErrorView {
        return ErrorView(
            status = HttpStatus.CONFLICT.value(),
            error = HttpStatus.CONFLICT.name,
            message = e.message,
            path = request.servletPath
        )
    }
}