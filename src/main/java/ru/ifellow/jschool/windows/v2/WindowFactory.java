package ru.ifellow.jschool.windows.v2;

public class WindowFactory {

    private static int amountOfRectButton = 0;
    private static int amountOfRoundButton = 0;

    public static RectButton createRectButton(Point leftTop, Point rightTop, boolean active) {
        amountOfRectButton++;
        return new RectButton(leftTop, rightTop, active);
    }

    public static RoundButton createRoundButton(Point center, int radius, boolean active) {
        amountOfRoundButton++;
        return new RoundButton(center, radius, active);
    }

    public static int getAmountOfRectButton() {
        return amountOfRectButton;
    }

    public static int getAmountOfRoundButton() {
        return amountOfRoundButton;
    }

    public static int getWindowCount() {
        return amountOfRectButton + amountOfRoundButton;
    }

    public static void reset() {
        amountOfRectButton = 0;
        amountOfRoundButton = 0;
    }
}
