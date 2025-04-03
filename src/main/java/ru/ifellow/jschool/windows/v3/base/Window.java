package ru.ifellow.jschool.windows.v3.base;

import ru.ifellow.jschool.windows.v3.Desktop;
import ru.ifellow.jschool.windows.v3.Point;
import ru.ifellow.jschool.windows.v3.iface.Moveable;
import ru.ifellow.jschool.windows.v3.iface.Resizable;

import java.util.Objects;

public abstract class Window implements Moveable, Resizable {

    private boolean active;

    public Window(boolean active) {
        this.active = active;
    }

    public abstract void moveTo(int x, int y);

    public  abstract void moveRel(int dx, int dy) ;

    public  abstract void resize(double ratio) ;

    public  abstract boolean isInside (int x, int y) ;

    public  abstract boolean isInside (Point point) ;

    public abstract boolean isFullyVisibleOnDesktop(Desktop desktop);

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Window window = (Window) o;
        return active == window.active;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(active);
    }
}
