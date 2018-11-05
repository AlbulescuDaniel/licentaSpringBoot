package net.licenta.error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Validation Failed",
        ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  ResponseEntity<ErrorDetails> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest req) {
    List<String> details = new ArrayList<>();
    details.add(ex.getCause().getCause().getLocalizedMessage().split("\\(")[2].replace(")", ""));
    ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Unique constraint validation", details);
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ErrorDetailsNotFound.class)
  public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ErrorDetailsNotFound ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getDetails());
    ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), details);
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ErrorDetailsForbidden.class)
  public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ErrorDetailsForbidden ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getDetails());
    ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), details);
    return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(ErrorDetailsBadRequest.class)
  public final ResponseEntity<ErrorDetails> handleErrorDetailsBadRequest(ErrorDetailsBadRequest ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getDetails());
    ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), details);
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
