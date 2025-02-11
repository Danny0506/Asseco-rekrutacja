package com.rekrutacja.asseco.error;

import static com.rekrutacja.asseco.error.ReasonCode.*;
import static java.lang.String.format;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
@Order(HIGHEST_PRECEDENCE)
@Slf4j
public class ControllerAdvisor {

  private static final String SOURCE = "ASSECO_RECRUITMENT_API";
  private static final String LOG_CONSTANT = "Error was occurred. Message: %s ";

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorMessage> handleHttpRequestMethodNotSupportedException(
      final HttpRequestMethodNotSupportedException exception) {
    log.info(format(LOG_CONSTANT, exception.getMessage()));
    log.debug(format(LOG_CONSTANT, exception.getMessage()));

    final ErrorMessage errorMessage =
        new ErrorMessage(
            SOURCE, LocalDateTime.now(), METHOD_NOT_ALLOW.name(), exception.getMessage());

    return new ResponseEntity<>(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorMessage> handleMethodArgumentTypeMismatchException(
      final MethodArgumentTypeMismatchException exception) {
    log.info(format(LOG_CONSTANT, exception.getMessage()));
    log.debug(format(LOG_CONSTANT, exception.getMessage()));

    final ErrorMessage errorMessage =
        new ErrorMessage(
            SOURCE, LocalDateTime.now(), ARGUMENT_TYPE_MISMATCH.name(), exception.getMessage());

    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MissingPathVariableException.class)
  public ResponseEntity<ErrorMessage> handleMissingPathVariableException(
      final MissingPathVariableException exception) {
    log.info(format(LOG_CONSTANT, exception.getMessage()));
    log.debug(format(LOG_CONSTANT, exception.getMessage()));

    final ErrorMessage errorMessage =
        new ErrorMessage(
            SOURCE, LocalDateTime.now(), MISSING_PATH_VARIABLE.name(), exception.getMessage());

    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(
      final HttpMessageNotReadableException exception) {
    log.info(format(LOG_CONSTANT, exception.getMessage()));
    log.debug(format(LOG_CONSTANT, exception.getMessage()));

    final ErrorMessage errorMessage =
        new ErrorMessage(
            SOURCE,
            LocalDateTime.now(),
            MESSAGE_NOT_READABLE.name(),
            "Required request body is missing.");

    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
      final MethodArgumentNotValidException exception) {
    log.info(format(LOG_CONSTANT, exception.getMessage()));
    log.debug(format(LOG_CONSTANT, exception.getMessage()));

    final ErrorMessage errorMessage =
        new ErrorMessage(
            SOURCE,
            LocalDateTime.now(),
            METHOD_ARGUMENT_NOT_VALID.name(),
            "Method argument contain illegal value.");

    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(WebClientResponseException.class)
  public ResponseEntity<ErrorMessage> handleRestClientException(
      final WebClientResponseException exception) {
    log.info(format(LOG_CONSTANT, exception.getMessage()));
    log.debug(format(LOG_CONSTANT, exception.getMessage()));

    final ErrorMessage errorMessage =
        new ErrorMessage(
            SOURCE, LocalDateTime.now(), REST_CLIENT_ERROR.name(), "Client returned a error.");

    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }
}
