package mineField;

import java.awt.*;

public class MineFieldShape {
    private final MineField mineField;
    static final int GRID_DIMENSION = 20;
    static final int CELL_SIZE = 20;

    public MineFieldShape(MineField mineField) {
        this.mineField = mineField;
    }

    public void draw(Graphics gc) throws Exception {
        Color oldColor = gc.getColor();

        for (int row = 0; row < GRID_DIMENSION; row++) {
            for (int col = 0; col < GRID_DIMENSION; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                Color borderColor = mineField.getBorderColor(row, col);
                String label = mineField.getLabel(row, col);
                if (borderColor == null || label == null)
                    throw new Exception("Out of bounds error");

                gc.setColor(Color.GRAY);
                gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);

                gc.setColor(borderColor);
                gc.drawRect(x, y, CELL_SIZE - 1, CELL_SIZE - 1); // subtract 1 for 'padding' effect and so borders don't overlap

                gc.setColor(Color.BLACK);
                FontMetrics fm = gc.getFontMetrics();
                int textX = x + (CELL_SIZE - fm.stringWidth(label)) / 2;
                int textY = y + ((CELL_SIZE - fm.getHeight()) / 2) + fm.getAscent();
                gc.drawString(label, textX, textY);
            }
        }
        gc.setColor(oldColor);
    }
}
