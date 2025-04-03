package ru.ifellow.jschool.windows.v2;

import ru.ifellow.jschool.base.StringOperations;

import java.util.Arrays;
import java.util.Objects;

public class ComboBox {
    private Point topLeft;
    private Point bottomRight;
    private int width;
    private int height;
    private boolean active;
    private String[] lines;
    private Integer selected;

    public ComboBox(Point topLeft, Point bottomRight, boolean active, String[] lines, Integer selected) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.active = active;

        if (lines != null) this.lines = lines.clone();

        if(bottomRight.equals(topLeft)) {
            width = 1;
            height = 1;
        }
        else {
            this.width = bottomRight.getX() - topLeft.getX();
            this.height = bottomRight.getY() - topLeft.getY();
        }
        this.selected = selected;
    }

    public ComboBox(int xLeft, int yTop, int width, int height, boolean active, String[] lines, Integer selected) {
        this(new Point(xLeft, yTop), new Point(xLeft + width, yTop + height), active, lines, selected);
    }

    public ComboBox(Point topLeft, Point bottomRight,  String[] lines, Integer selected) {
        this(topLeft, bottomRight, true, lines, selected);
    }

    public ComboBox(int xLeft, int yTop, int width, int height, String[] lines, Integer selected) {
        this(xLeft, yTop, width, height, true, lines, selected);
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

    public String[] getLines() {
        return lines;
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }

    public Integer getSelected() {
        return selected;
    }
    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public String[] getLinesSlice(int from, int to) {
        if (lines == null) return null;

        to = Math.min(to, lines.length);
        String[] slice = new String[to - from];

        for(int i = from; i < to; i++) slice[i] = lines[i];

        return slice;
    }
    public String getLine(int index) {
        if (lines == null) return null;
        if(index < 0 || index >= lines.length) return null;
        if (lines[index] == null) return null;
        else return lines[index];
    }

    public void setLine(int index, String line) {
        if (lines == null) return;
        if(index < 0 || index >= lines.length) return;
        lines[index] = line;
    }

    public Integer findLine(String line) {

        if (lines == null) return null;

        for(int i = 0; i < lines.length; i++)
            if (lines[i].equals(line)) return i;

        return null;
    }

    public void reverseLineOrder() {
        if (lines == null) return;
        int arraySize = lines.length;

        for (int i = 0; i < lines.length / 2; i++) {
            String temp = lines[i];
            lines[i] = lines[arraySize - 1 - i];
            lines[arraySize - 1 - i] = temp;
        }
    }

    public void reverseLines() {
        if (lines == null) return;
        for (int i = 0; i < lines.length; i++) {
            lines[i] = StringOperations.reverse(lines[i]);
        }
    }

    public void duplicateLines() {
        if (lines == null) return;
        String[] newLines = new String[lines.length * 2];

        for (int i = 0; i < lines.length; i++) {
            newLines[2 * i] = lines[i];
            newLines[2 * i + 1] = lines[i];
        }
        lines = newLines;
    }

    public void removeOddLines() {
        if (lines == null) return;
        String[] newLines = new String[lines.length / 2];

        for (int i = 0, j = 0; i < lines.length; i++) {
            if (i % 2 == 0) {
                newLines[j] = lines[i];
                j++;
            }
        }

        lines = newLines;
    }

    public boolean isSortedDescendant() {
        if (lines == null) return true;

        for (int i = 1; i < lines.length; i++) {
            if (lines[i - 1].compareTo(lines[i]) < 0) return false;
        }
        return true;
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

    public boolean isIntersects(ComboBox comboBox) {
        int x1 = this.topLeft.getX();
        int y1 = this.topLeft.getY();

        int x2 = comboBox.getTopLeft().getX();
        int y2 = comboBox.getTopLeft().getY();

        return  (x2 - x1) <= this.width && (y2 -y1) <= this.height  &&
                (x1 - x2) <= comboBox.width && (y1 - y2) <= comboBox.height;
    }

    public boolean isInside(ComboBox comboBox) {
        return comboBox.isInside(topLeft) && comboBox.isInside(bottomRight);
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        return topLeft.isVisibleOnDesktop(desktop) && bottomRight.isVisibleOnDesktop(desktop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComboBox comboBox = (ComboBox) o;
        return width == comboBox.width && height == comboBox.height && active == comboBox.active && Objects.equals(topLeft, comboBox.topLeft) && Objects.equals(bottomRight, comboBox.bottomRight) && Objects.deepEquals(lines, comboBox.lines) && Objects.equals(selected, comboBox.selected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, bottomRight, width, height, active, Arrays.hashCode(lines), selected);
    }
}
