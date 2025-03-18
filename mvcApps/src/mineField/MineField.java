package mineField;

import mvc.Model;

import java.awt.*;
import java.util.HashSet;

import static mineField.MineFieldShape.GRID_DIMENSION;

public class MineField extends Model {
    private int r = 0, c = 0;
    private final Cell[][] grid;
    private boolean gameWon;
    static final int PERCENT_MINED = 5;

    public MineField() {
        grid = new Cell[GRID_DIMENSION][GRID_DIMENSION];
        gameWon = false;

        // initialize grid
        initMineField();
    }

    public String getLabel(int r, int c) {
        if (!inBounds(r, c))
            return null;

        Cell cell = grid[r][c];
        return cell.getLabel();
    }

    public Color getBorderColor(int r, int c) {
        if (!inBounds(r, c))
            return null;

        Cell cell = grid[r][c];
        return cell.getHasBeenMined() ? Color.WHITE : Color.BLACK;
    }

    public void move(String direction) throws Exception {
        switch (direction) {
            case "N" -> r -= 1;
            case "NE" -> {
                r -= 1;
                c += 1;
            }
            case "E" -> c += 1;
            case "SE" -> {
                r += 1;
                c += 1;
            }
            case "S" -> r += 1;
            case "SW" -> {
                r += 1;
                c -= 1;
            }
            case "W" -> c -= 1;
            case "NW" -> {
                r -= 1;
                c -= 1;
            }
            default -> throw new Exception("Invalid direction: " + direction);
        }
        if (inBounds(r, c)) {
            revealPosition(r, c);
        } else {
            throw new Exception();
        }
        changed();
    }

    private void initMineField() {
        HashSet<String> mineLocations = new HashSet<>();

        // assign 5% of random cells to be a mine
        int numberOfMines = (GRID_DIMENSION * GRID_DIMENSION * PERCENT_MINED) / 100;
        for (int i = 0; i < numberOfMines; i++) {
            int x = 0, y = 0;
            String coord;
            do {
                x = (int) (Math.random() * GRID_DIMENSION);
                y = (int) (Math.random() * GRID_DIMENSION);
                coord = x + "," + y;
            } while (x == 0 && y == 0 || x == GRID_DIMENSION - 1 && y == GRID_DIMENSION - 1 || mineLocations.contains(coord));
            mineLocations.add(coord);
        }

        for (int r = 0; r < GRID_DIMENSION; r++) {
            for (int c = 0; c < GRID_DIMENSION; c++) {
                String coord = r + "," + c;
                boolean isMine = mineLocations.contains(coord);
                int numMinesNearby = isMine ? -1 : countNearbyMines(r, c, mineLocations);
                grid[r][c] = new Cell(isMine, false, "?", numMinesNearby);
            }
        }

        // reveal the starting grid point
        revealPosition(0, 0);
    }

    private int countNearbyMines(int row, int col, HashSet<String> mineLocations) {
        int count = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                // skip the current cell itself
                if (r == row && c == col) continue;
                // ensure valid bounds
                if (r < 0 || r >= GRID_DIMENSION || c < 0 || c >= GRID_DIMENSION) continue;

                String coord = r + "," + c;
                if (mineLocations.contains(coord)) {
                    count++;
                }
            }
        }
        return count;
    }

    private void revealPosition(int r, int c) {
        Cell cell = grid[r][c];
        cell.setHasBeenMined(true);
        cell.setLabel(String.valueOf(cell.getNumMinesNearby()));
    }

    private boolean inBounds(int r, int c) {
        return 0 <= r && r < GRID_DIMENSION && 0 <= c && c < GRID_DIMENSION;
    }
}
