package Exceptions;

// Исключение для неподдерживаемой фигуры
public class FigureNotSupportedException extends UnsupportedOperationException {
  public FigureNotSupportedException(String message) {
    super(message);
  }
}
