package com.github.omaraouini.quickcrud.base.advice;

import com.github.omaraouini.quickcrud.base.dto.ErrorListReponse;
import com.github.omaraouini.quickcrud.base.dto.ErrorResponse;
import com.github.omaraouini.quickcrud.base.exception.ResourceNotFoundException;
import com.github.omaraouini.quickcrud.base.utils.ValidationMsgConst;
import com.github.omaraouini.quickcrud.base.utils.Translator;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author aouin
 * Date: 04/03/2023
 * Time: 17:35
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ErrorResponse.builder()
                .message(exception.getLocalizedMessage())
                .path(null)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorListReponse handleConstraintViolationException(ConstraintViolationException exception) {
        return ErrorListReponse.builder()
                .message(Translator.toLocale(ValidationMsgConst.VALIDATION_ERRORS))
                .content(
                        exception.getConstraintViolations()
                                .stream()
                                .map(constraintViolation -> {
                                    String message = Translator.toLocale(constraintViolation.getMessageTemplate().replace("{", "").replace("}", ""));
                                            return ErrorResponse.builder()
                                                    .path(constraintViolation.getPropertyPath().toString())
                                                    .message(message)
                                                    .build();
                                        }
                                )
                                .toList())
                .build();
    }
}
