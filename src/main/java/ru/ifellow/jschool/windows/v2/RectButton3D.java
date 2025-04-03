package ru.ifellow.jschool.windows.v2;

import java.util.Objects;

public class RectButton3D {
    private Point topLeft;
    private Point bottomRight;
    private int width;
    private int height;
    private boolean active;
    private String text;
    private int zHeight;

    public RectButton3D(Point topLeft, Point bottomRight, boolean active, String text, int zHeight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.active = active;
        this.text = text;
        this.zHeight = zHeight;
        if(bottomRight.equals(topLeft)) {
            width = 1;
            height = 1;
        }
        else {
            this.width = bottomRight.getX() - topLeft.getX();
            this.height = bottomRight.getY() - topLeft.getY();
        }
    }

    public RectButton3D(int xLeft, int yTop, int width, int height, boolean active, String text, int zHeight) {
        this(new Point(xLeft, yTop), new Point(xLeft + width, yTop + height), active, text, zHeight);
    }

    public RectButton3D(Point topLeft, Point bottomRight, String text, int zHeight) {
        this(topLeft, bottomRight, true, text, zHeight);
    }

    public RectButton3D(int xLeft, int yTop, int width, int height, String text, int zHeight) {
        this(xLeft, yTop, width, height, true, text, zHeight);
    }

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

    public int getzHeight() {
        return zHeight;
    }

    public void setzHeight(int zHeight) {
        this.zHeight = zHeight;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void moveTo(int x, int y) {
        topLeft.setX(x);
        topLeft.setY(y);
        bottomRight.setX(x + width);
        bottomRight.setY(y + height);
    }

    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
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

    public boolean isIntersects(RectButton rectButton) {
        int x1 = this.topLeft.getX();
        int y1 = this.topLeft.getY();

        int x2 = rectButton.getTopLeft().getX();
        int y2 = rectButton.getTopLeft().getY();

        return  (x2 - x1) <= this.width && (y2 -y1) <= this.height  &&
                (x1 - x2) <= rectButton.getWidth() && (y1 - y2) <= rectButton.getHeight();
    }

    public boolean isInside(RectButton rectButton) {
        return rectButton.isInside(topLeft) && rectButton.isInside(bottomRight);
    }

    public boolean isInside(RectButton3D rectButton3D) {
        return rectButton3D.isInside(topLeft) &&
                rectButton3D.isInside(bottomRight) &&
                this.height <= rectButton3D.getHeight();
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return topLeft.isVisibleOnDesktop(desktop) && bottomRight.isVisibleOnDesktop(desktop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RectButton3D that = (RectButton3D) o;
        return width == that.width && height == that.height && active == that.active && zHeight == that.zHeight && Objects.equals(topLeft, that.topLeft) && Objects.equals(bottomRight, that.bottomRight) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, bottomRight, width, height, active, text, zHeight);
    }
}
