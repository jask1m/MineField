package mineField;

import mvc.*;
import java.awt.*;

public class MineFieldView extends View {
    public MineFieldView(MineField cell) {
        super(cell);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        Stoplight light = (Stoplight)model;
        StoplightShape shape = new StoplightShape(light);
        shape.draw((Graphics2D) gc);
        gc.setColor(oldColor);
    }
}
