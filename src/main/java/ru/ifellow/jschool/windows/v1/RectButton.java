package ru.ifellow.jschool.windows.v1;

import java.util.Objects;

public class RectButton {

    private Point topLeft;
    private Point bottomRight;
    private boolean active;
    private int width;
    private int height;

    public RectButton(Point topLeft, Point bottomRight, boolean active) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.active = active;

        if( topLeft.equals(bottomRight) ) {
            width = 1;
            height = 1;
        }
        else {
            width = bottomRight.getX() - topLeft.getX();
            height = bottomRight.getY() - topLeft.getY();
        }
    }
    public RectButton(int xLeft, int yTop, int width, int height, boolean active) {
        this(new Point(xLeft, yTop), new Point(width + xLeft, height + yTop), active);
    }

    public RectButton (Point topLeft, Point bottomRight) {
        this(topLeft, bottomRight, true);
    }

    public RectButton(int xLeft, int yTop, int width, int height) {
        this(xLeft, yTop, width, height, true);
    }

    // у меня мысль что возможно было бы лучше вернуть копию чтобы не нарушать инкапсуляцию? В данном случае
    // я бы сделал чтобы класс Point реализовывал метод cloneable
    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public boolean isActive() {
        return active;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveTo(int x, int y) {
        this.topLeft = new Point(x, y);
        this.bottomRight= new Point(x + width, y + height);
    }

    public void moveRel(int dx, int dy) {
        this.topLeft.moveRel(dx, dy);
        this.bottomRight.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        if (ratio > 0) {
            this.width = (int) (width * ratio);
            this.height = (int) (height * ratio);
        }
        else {
            this.width = 1;
            this.height = 1;
        }
        this.bottomRight = new Point(topLeft.getX() + width, topLeft.getY() + height);
    }

    public boolean isInside (int x, int y) {
        return  (x <= bottomRight.getX()) &&
                (y <= bottomRight.getY()) &&
                (x >= topLeft.getX()) &&
                (y >= topLeft.getY());
    }

    public boolean isInside (Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersect(RectButton rectButton) {
        // определим левые верхние точки обоих прямоугольников чтобы код был читабельнее
        int x1 = this.topLeft.getX();
        int y1 = this.topLeft.getY();

        int x2 = rectButton.getTopLeft().getX();
        int y2 = rectButton.getTopLeft().getY();

        return  (x2 - x1) <= this.width && (y2 -y1) <= this.height  &&
                (x1 - x2) <= rectButton.width && (y1 - y2) <= rectButton.height;
    }

    public boolean isInside(RectButton rectButton) {
        return rectButton.isInside(topLeft) && rectButton.isInside(bottomRight) ;
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {

        return topLeft.isVisibleOnDesktop(desktop) && bottomRight.isVisibleOnDesktop(desktop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RectButton that = (RectButton) o;
        return active == that.active && width == that.width && height == that.height && Objects.equals(topLeft, that.topLeft) && Objects.equals(bottomRight, that.bottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, bottomRight, active, width, height);
    }
}
