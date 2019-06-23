package com.jefff.gr.homework.exceptions;

import com.jefff.gr.homework.model.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
public class AppExceptionHandler
{
    public static Logger _log = LoggerFactory.getLogger(AppExceptionHandler.class);

    // Annotate the method indicating what exceptions the class handles.
    @ExceptionHandler(value = {UsageException.class})
    public ResponseEntity<ErrorMessage> handleUserServiceException(UsageException ex,
                                                                   WebRequest webRequest
    )
    {
        String message = ex.getMessage();
        _log.warn(String.format("AppExceptionHandler.handleUserServiceException(): caught Exception: %s", message));

        ErrorMessage errorMessage = new ErrorMessage(new Date(), message);
        // build the response that will be sent back to the client.
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
