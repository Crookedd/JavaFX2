package org.example.javafx2;

import geometry2d.Circle;
import geometry2d.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.Random;

public class Controller {
    @FXML
    private Canvas canvas;

    @FXML
    private Button drawCircleButton;

    @FXML
    private Button drawRectangleButton;

    private final Random random = new Random();

    @FXML
    public void initialize() {
        drawCircleButton.setOnAction(event -> drawCircle());
        drawRectangleButton.setOnAction(event -> drawRectangle());
    }

    private void drawCircle() {
        double radius = random.nextDouble() * 5 + 2;
        Circle circle = new Circle(radius);
        drawFigure(circle);
    }

    private void drawRectangle() {
        double width = random.nextDouble() * 5 + 10;
        double height = random.nextDouble() * 5 + 20;
        Rectangle rectangle = new Rectangle(width, height);
        drawFigure(rectangle);
    }

    private void drawFigure(geometry2d.Figure figure) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(randomColor());

        if (figure instanceof Circle circle) {
            double x = random.nextDouble() * (canvas.getWidth() - 2 * circle.area() / Math.PI);
            double y = random.nextDouble() * (canvas.getHeight() - 2 * circle.area() / Math.PI);
            gc.fillOval(x, y, circle.area() / Math.PI * 2, circle.area() / Math.PI * 2);
        } else if (figure instanceof Rectangle rectangle) {
            double x = random.nextDouble() * (canvas.getWidth() - rectangle.area());
            double y = random.nextDouble() * (canvas.getHeight() - rectangle.area() / rectangle.area());
            gc.fillRect(x, y, rectangle.area(), rectangle.area() / rectangle.area());
        }
    }

    private Color randomColor() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}