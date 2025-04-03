package ru.ifellow.jschool.windows.v3.base;

import ru.ifellow.jschool.windows.v3.Desktop;
import ru.ifellow.jschool.windows.v3.Point;

import java.util.Objects;

public abstract class RoundWindow extends Window {

    private Point center;
    private int radius;
    private String text;

    public RoundWindow(Point center, int radius, String text, boolean active) {
        super(active);
        this.center = center;
        this.radius = radius;
        this.text = text;
    }

    public RoundWindow(int xCenter, int yCenter, int radius, String text, boolean active) {
        this(new Point(xCenter, yCenter), radius, text, active);
    }

    public RoundWindow(Point center, int radius, String text) {
        this(center, radius, text, true);
    }

    public RoundWindow(int xCenter, int yCenter, int radius, String text) {
        this(xCenter, yCenter, radius, text, true);
    }

    public void moveTo(int x, int y) {
        center = new Point(x, y);
    }

    public void moveRel(int dx, int dy) {
        center = new Point(center.getX() + dx, center.getY() + dy);
    }

    public void resize(double ratio) {
        radius = radius * ratio > 1 ? (int) (radius * ratio) : 1;
    }

    public boolean isInside(int x, int y) {
        return (center.getX() - x) * (center.getX() - x) + (center.getY() - y) * (center.getY() - y)
                <= radius * radius;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return center.getX() >= radius && center.getY() >= radius &&
                center.getX() + radius <= desktop.getWidth() &&
                center.getY() + radius <= desktop.getHeight();
    }

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public String getText() {
        return text;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoundWindow that = (RoundWindow) o;
        return radius == that.radius && Objects.equals(center, that.center) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), center, radius, text);
    }
}
