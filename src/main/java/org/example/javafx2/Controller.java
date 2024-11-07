package org.example.javafx2;

import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {
    @FXML
    private Canvas canvas;

    @FXML
    private Button drawCircleButton;

    @FXML
    private Button drawRectangleButton;

    @FXML
    private ColorPicker colorPicker;

    private final List<Figure> figures = new ArrayList<>();
    private final Random random = new Random();
    private geometry2d.Figure selectedFigure = null;
    private double offsetX, offsetY;

    @FXML
    public void initialize() {
        drawCircleButton.setOnAction(event -> drawCircle());
        drawRectangleButton.setOnAction(event -> drawRectangle());

        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseDragged(this::handleMouseDragged);
        canvas.setOnMouseReleased(this::handleMouseReleased);
    }

    private void drawCircle() {
        double radius = random.nextDouble() * 5 + 2; // случайный радиус от 10 до 60
        Circle circle = new Circle(radius);
        circle.setX(random.nextDouble() * (canvas.getWidth() - 2 * radius));
        circle.setY(random.nextDouble() * (canvas.getHeight() - 2 * radius));
        circle.setColor(colorPicker.getValue()); // Устанавливаем цвет выбранный пользователем
        figures.add(circle);
        drawAllFigures();
    }

    private void drawRectangle() {
        double width = random.nextDouble() * 50 + 20; // случайная ширина от 20 до 120
        double height = random.nextDouble() * 50 + 20; // случайная высота от 20 до 120
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setX(random.nextDouble() * (canvas.getWidth() - width));
        rectangle.setY(random.nextDouble() * (canvas.getHeight() - height));
        rectangle.setColor(colorPicker.getValue()); // Устанавливаем цвет выбранный пользователем
        figures.add(rectangle);
        drawAllFigures();
    }

    private void drawAllFigures() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (geometry2d.Figure figure : figures) {
            if (figure instanceof Circle) {
                drawCircleOnCanvas((Circle) figure);
            } else if (figure instanceof Rectangle) {
                drawRectangleOnCanvas((Rectangle) figure);
            }
        }
    }

    private void drawCircleOnCanvas(Circle circle) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(circle.getColor());
        gc.fillOval(circle.getX() - circle.area() / Math.PI, circle.getY() - circle.area() / Math.PI,
                circle.area() / Math.PI * 2, circle.area() / Math.PI * 2);
    }

    private void drawRectangleOnCanvas(Rectangle rectangle) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(rectangle.getColor());
        gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }


    private void handleMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            for (int i = figures.size() - 1; i >= 0; i--) {
                geometry2d.Figure figure = figures.get(i);
                if (isMouseOverFigure(event.getX(), event.getY(), figure)) {
                    selectedFigure = figure;
                    offsetX = event.getX() - figure.getX();
                    offsetY = event.getY() - figure.getY();
                    figures.remove(i); // Удаляем из списка для перемещения на передний план
                    figures.add(figure); // Добавляем в конец списка
                    break;
                }
            }
        } else if (event.getButton() == MouseButton.SECONDARY) {
            for (geometry2d.Figure figure : figures) {
                if (isMouseOverFigure(event.getX(), event.getY(), figure)) {
                    changeFigureColor(figure);
                    drawAllFigures();
                    break;
                }
            }
        }
    }

    private void handleMouseDragged(MouseEvent event) {
        if (selectedFigure != null) {
            double newX = event.getX() - offsetX;
            double newY = event.getY() - offsetY;
            moveFigure(selectedFigure, newX, newY);
            drawAllFigures();
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        selectedFigure = null;
    }

    private boolean isMouseOverFigure(double mouseX, double mouseY, geometry2d.Figure figure) {
        if (figure instanceof Circle circle) {
            double centerX = circle.getX();
            double centerY = circle.getY();
            double radius = circle.area() / Math.PI;
            return Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2) <= Math.pow(radius, 2);
        } else if (figure instanceof Rectangle rectangle) {
            double rectX = rectangle.getX();
            double rectY = rectangle.getY();
            return mouseX >= rectX && mouseX <= rectX + rectangle.getWidth() &&
                    mouseY >= rectY && mouseY <= rectY + rectangle.getHeight();
        }
        return false;
    }

    private void moveFigure(geometry2d.Figure figure, double x, double y) {
        figure.setX(x);
        figure.setY(y);
    }

    private void changeFigureColor(geometry2d.Figure figure) {
        Color randomColor = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
        figure.setColor(randomColor);
    }
}