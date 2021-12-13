package ru.ershov.pro_education.exception.handler;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ershov.pro_education.exception.not_found.NotFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class JdbcExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    String handleBadRequest(HttpServletRequest req, DataAccessException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    String handleNotFoundEntity(Exception ex) {
        return ex.getMessage();
    }
}
