package com.arrays.demo.exception;

public class ArrayProcessingException extends RuntimeException {
  public ArrayProcessingException(String message) {
    super(message);
  }

  public ArrayProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
