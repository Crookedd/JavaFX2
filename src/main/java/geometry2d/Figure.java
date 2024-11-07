package geometry2d;


import javafx.scene.paint.Color;

public interface Figure {
    double area();
    double getX();
    double getY();
    void setX(double x);
    void setY(double y);
    Color getColor();
    void setColor(Color color);
}

