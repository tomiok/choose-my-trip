package com.tomiok.itineraryservice.web;

import com.tomiok.itineraryservice.usecase.gateways.CityNotFoundException;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
public class WebExceptionHandling {

  @ExceptionHandler({ IllegalArgumentException.class, MethodArgumentNotValidException.class,
                      EntityNotFoundException.class, CityNotFoundException.class})
  public ResponseEntity<?> handleBadRequests(final Throwable throwable, final NativeWebRequest req) {
    return create(HttpStatus.BAD_REQUEST, throwable.getMessage(), null);
  }

  @ExceptionHandler({ NullPointerException.class })
  public ResponseEntity<?> handleNullPointerException(final Throwable throwable, final NativeWebRequest req) {
    return create(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage(), req.getNativeRequest().toString());
  }

  private ResponseEntity<ErrorDto> create(HttpStatus status, String msg, String req) {
    return ResponseEntity.status(status).body(new ErrorDto(msg, req));
  }

  @Getter
  @Setter
  @AllArgsConstructor
  private class ErrorDto {

    private String message;
    private String req;
  }
}
