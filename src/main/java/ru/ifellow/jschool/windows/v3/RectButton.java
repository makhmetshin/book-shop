package ru.ifellow.jschool.windows.v3;

import ru.ifellow.jschool.windows.v3.base.RectWindow;

import java.util.Objects;

public class RectButton extends RectWindow {


    private String text;

    public RectButton(Point topLeft, Point bottomRight, String text, boolean active) {
        super(topLeft, bottomRight, active);
        this.text = text;

    }
    public RectButton(int xLeft, int yTop, int width, int height, String text, boolean active) {
        this(new Point(xLeft, yTop), new Point(xLeft + width, yTop + height), text, active);
    }

    public RectButton (Point topLeft, Point bottomRight, String text) {
        this(topLeft, bottomRight, text, true);
    }

    public RectButton(int xLeft, int yTop, int width, int height, String text) {
        this(xLeft, yTop, width, height, text, true);
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RectButton that = (RectButton) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}
