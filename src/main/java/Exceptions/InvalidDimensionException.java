package Exceptions;

// Исключение для неверных размеров
public class InvalidDimensionException extends IllegalArgumentException {
  public InvalidDimensionException(String message) {
    super(message);
  }
}

