package mineField;

import mvc.*;
import tools.Utilities;
import java.awt.*;

public class MineFieldView extends View {
    public MineFieldView(MineField mineField) {
        super(mineField);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        MineField mineField = (MineField)model;
        MineFieldShape shape = new MineFieldShape(mineField);
        try {
            shape.draw((Graphics2D) gc);
        } catch (Exception e) {
            Utilities.error(e);
        }
        gc.setColor(oldColor);
    }
}
