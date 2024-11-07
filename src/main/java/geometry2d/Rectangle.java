package geometry2d;

// Класс Rectangle
import javafx.scene.paint.Color;

public class Rectangle implements Figure {
    private final double width;
    private final double height;
    private double x;
    private double y;
    private Color color;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
        this.color = Color.BLACK; // Установим цвет по умолчанию
    }

    @Override
    public double area() {
        return width * height;
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


    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}