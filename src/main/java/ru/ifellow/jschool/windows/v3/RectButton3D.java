package ru.ifellow.jschool.windows.v3;

import ru.ifellow.jschool.windows.v3.base.RectWindow;

import java.util.Objects;

public class RectButton3D extends RectWindow {

    private String text;
    private int zHeight;

    public RectButton3D(Point topLeft, Point bottomRight, boolean active, String text, int zHeight) {
        super(topLeft, bottomRight, active);
        this.text = text;
        this.zHeight = zHeight;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RectButton3D that = (RectButton3D) o;
        return zHeight == that.zHeight && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, zHeight);
    }
}
