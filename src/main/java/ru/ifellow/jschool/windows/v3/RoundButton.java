package ru.ifellow.jschool.windows.v3;

import ru.ifellow.jschool.windows.v3.base.RoundWindow;

public class RoundButton extends RoundWindow {

    public RoundButton(Point center, int radius, String text, boolean active) {
        super(center, radius, text, active);
    }

    public RoundButton(int xCenter, int yCenter, int radius, String text, boolean active) {
        this(new Point(xCenter, yCenter), radius, text, active);
    }

    public RoundButton(Point center, int radius, String text) {
        this(center, radius, text, true);
    }

    public RoundButton(int xCenter, int yCenter, int radius, String text) {
        this(xCenter, yCenter, radius, text, true);
    }


}
