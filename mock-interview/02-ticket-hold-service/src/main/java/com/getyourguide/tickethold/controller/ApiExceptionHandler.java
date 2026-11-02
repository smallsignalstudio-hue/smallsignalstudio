package com.getyourguide.tickethold.controller;

import com.getyourguide.tickethold.service.InvalidHoldException;
import com.getyourguide.tickethold.service.NotFoundException;
import com.getyourguide.tickethold.service.SeatNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ProblemDetail notFound(NotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(SeatNotAvailableException.class)
    ProblemDetail conflict(SeatNotAvailableException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(InvalidHoldException.class)
    ProblemDetail invalid(InvalidHoldException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }
}
