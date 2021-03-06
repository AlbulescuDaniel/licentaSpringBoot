package net.licenta.error;

import java.time.LocalDateTime;

public class ErrorDetailsBadRequest extends RuntimeException{
  private static final long serialVersionUID = 1L;
  private final LocalDateTime timestamp;
  private final String message;
  private final String details;

  public ErrorDetailsBadRequest(LocalDateTime timestamp, String message, String details) {
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }

  @Override
  public String getLocalizedMessage() {
    return null;
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
      return this;
  }
  
  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }
}
