package ru.ifellow.jschool.windows.v2;

import java.util.Objects;

public class RadioButton {
    private Point center;
    private int radius;
    private boolean active;
    private String text;
    private boolean checked;

    public RadioButton(Point center, int radius, boolean active, String text, boolean checked) {
        this.center = center;
        this.radius = radius;
        this.active = active;
        this.text = text;
        this.checked = checked;
    }


    public RadioButton(int xCenter, int yCenter, int radius, boolean active, String text, boolean checked) {
        this(new Point(xCenter, yCenter), radius, active, text, checked);
    }


    public RadioButton(Point center, int radius, String text, boolean checked) {
        this(center, radius,true, text, checked);
    }


    public RadioButton(int xCenter, int yCenter, int radius, String text, boolean checked) {
        this(new Point(xCenter, yCenter), radius, text, checked);
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

    public boolean isChecked() {
        return checked;
    }
    public void moveTo(int x, int y) {
        center = new Point(x, y);
    }

    public void moveTo(Point point) {
        center = point;
    }

    public void moveRel(int dx, int dy) {
        center = new Point(center.getX() + dx, center.getY() + dy);
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadioButton that = (RadioButton) o;
        return radius == that.radius && active == that.active && checked == that.checked && Objects.equals(center, that.center) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius, active, text, checked);
    }
}
