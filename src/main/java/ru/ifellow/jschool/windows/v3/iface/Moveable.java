package ru.ifellow.jschool.windows.v3.iface;

import ru.ifellow.jschool.windows.v3.Point;

public interface Moveable {
    void moveTo(int x, int y);

    default void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    void moveRel(int dx, int dy);

}
