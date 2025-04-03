package ru.ifellow.jschool.windows.v3.base;

import ru.ifellow.jschool.windows.v3.Desktop;
import ru.ifellow.jschool.windows.v3.Point;

import java.util.Objects;

public abstract class RectWindow extends Window {
    private Point topLeft;
    private Point bottomRight;
    private int width;
    private int height;

    public RectWindow(Point topLeft, Point bottomRight, boolean active) {
        super(active);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;

        if(bottomRight.equals(topLeft)) {
            width = 1;
            height = 1;
        }
        else {
            this.width = bottomRight.getX() - topLeft.getX();
            this.height = bottomRight.getY() - topLeft.getY();
        }

    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void moveTo(int x, int y) {
        topLeft.setX(x);
        topLeft.setY(y);
        bottomRight.setX(x + width);
        bottomRight.setY(y + height);
    }


    public void moveRel(int dx, int dy) {
        topLeft.moveRel(dx, dy);
        bottomRight.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        if (ratio * width < 1 ) width = 1;
        else width = (int) (ratio * width);

        if (ratio * height < 1 ) height = 1;
        else height = (int) (ratio * height);

        bottomRight.setX(topLeft.getX() + width);
        bottomRight.setY(topLeft.getY() + height);
    }

    public boolean isInside(int x, int y) {
        return  (x <= bottomRight.getX()) &&
                (y <= bottomRight.getY()) &&
                (x >= topLeft.getX()) &&
                (y >= topLeft.getY());
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(RectWindow rectWindow) {
        int x1 = this.topLeft.getX();
        int y1 = this.topLeft.getY();

        int x2 = rectWindow.getTopLeft().getX();
        int y2 = rectWindow.getTopLeft().getY();

        return  (x2 - x1) <= this.width && (y2 -y1) <= this.height  &&
                (x1 - x2) <= rectWindow.width && (y1 - y2) <= rectWindow.height;
    }

    public boolean isInside(RectWindow rectWindow) {
        return rectWindow.isInside(topLeft) && rectWindow.isInside(bottomRight);
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return topLeft.isVisibleOnDesktop(desktop) && bottomRight.isVisibleOnDesktop(desktop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RectWindow that = (RectWindow) o;
        return width == that.width && height == that.height && Objects.equals(topLeft, that.topLeft) && Objects.equals(bottomRight, that.bottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), topLeft, bottomRight, width, height);
    }
}
