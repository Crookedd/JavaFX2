package geometry2d;

import Exceptions.FigureNotSupportedException;

// Класс Rectangle
public class Rectangle implements Figure {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new FigureNotSupportedException("Ширина и высота должны быть положительными числами.");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Прямоугольник: ширина - " + width + " высота - " + height;
    }
}