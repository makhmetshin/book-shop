package ru.ifellow.jschool.windows.v3;

import ru.ifellow.jschool.base.StringOperations;
import ru.ifellow.jschool.windows.v3.base.RectWindow;

import java.util.Arrays;
import java.util.Objects;

public class ComboBox extends RectWindow {

    private String[] lines;
    private Integer selected;

    public ComboBox(Point topLeft, Point bottomRight, boolean active, String[] lines, Integer selected) {
        super(topLeft, bottomRight, active);

        if (lines != null) this.lines = lines.clone();

        this.selected = selected;
    }

    public ComboBox(int xLeft, int yTop, int width, int height, boolean active, String[] lines, Integer selected) {
        this(new Point(xLeft, yTop), new Point(xLeft + width, yTop + height), active, lines, selected);
    }

    public ComboBox(Point topLeft, Point bottomRight, String[] lines, Integer selected) {
        this(topLeft, bottomRight, true, lines, selected);
    }

    public ComboBox(int xLeft, int yTop, int width, int height, String[] lines, Integer selected) {
        this(xLeft, yTop, width, height, true, lines, selected);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComboBox comboBox = (ComboBox) o;
        return Objects.deepEquals(lines, comboBox.lines) && Objects.equals(selected, comboBox.selected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(lines), selected);
    }
}
