package geometry2d;

import Exceptions.FigureNotSupportedException;

// Класс Rectangle
public record Rectangle(double width, double height) implements Figure {
    public Rectangle {
        if (width <= 0 || height <= 0) {
            throw new FigureNotSupportedException("Ширина и высота должны быть положительными числами.");
        }
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Прямоугольник: ширина - " + width + ", высота - " + height;
    }
}