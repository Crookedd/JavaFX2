package geometry2d;

import javafx.scene.paint.Color;

public class Circle implements Figure {
    private final double radius;
    private double x;
    private double y;
    private Color color;

    public Circle(double radius) {
        this.radius = radius;
        this.color = Color.BLACK; // Установим цвет по умолчанию
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}

