package ru.ifellow.jschool.windows.v2;

import java.util.Objects;

public class RoundButton {

    private boolean active;
    private Point center;
    private int radius;
    private String text;

    public RoundButton(Point center, int radius, String text,  boolean active) {
        this.active = active;
        this.center = center;
        this.radius = radius;
        this.text = text;
    }

    public RoundButton(int xCenter, int yCenter, int radius, String text, boolean active) {
        this(new Point(xCenter, yCenter), radius, text, active);
    }

    public RoundButton(Point center,  int radius, String text) {
        this(center, radius, text, true);
    }

    public RoundButton(int xCenter, int yCenter, int radius, String text) {
        this(xCenter, yCenter, radius, text, true);
    }

    public String getText() {
        return text;
    }

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isActive() {
        return active;
    }

    public void moveTo(int x, int y) {
        center = new Point(x, y);
    }

    public void moveTo(Point point) {
        center = point;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void moveRel(int dx, int dy) {
        center = new Point(center.getX() + dx, center.getY() + dy);
    }

    public void resize(double ratio) {
        radius = radius * ratio > 1 ? (int)(radius * ratio) : 1;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundButton that = (RoundButton) o;
        return active == that.active && radius == that.radius && Objects.equals(center, that.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, center, radius);
    }
}
