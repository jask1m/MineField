package mineField;

import mvc.Model;

import java.awt.*;
import java.util.HashSet;

import static mineField.MineFieldShape.GRID_DIMENSION;

public class MineField extends Model {
    /*
    TODO:
     [.] throw error when sargent rock steps on a mine
     [.] highlight the goal cell green
     [.] when user goes out of bounds, reset
     [.] update gameWon and throw error if user moves after gameWon
     */
    private int r = 0, c = 0;
    private final Cell[][] grid;
    private boolean gameOver;
    static final int PERCENT_MINED = 5;

    public MineField() {
        grid = new Cell[GRID_DIMENSION][GRID_DIMENSION];
        gameOver = false;

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
        if (reachedEndPosition(r, c))
            return Color.GREEN;

        Cell cell = grid[r][c];
        return cell.getHasBeenMined() ? Color.WHITE : Color.BLACK;
    }

    public void move(String direction) throws Exception {
        if (gameOver) {
            throw new Exception("Game is over! Please select 'New' from the File Menu");
        }

        int newR = r;
        int newC = c;
        switch (direction) {
            case "N" -> newR -= 1;
            case "NE" -> {
                newR -= 1;
                newC += 1;
            }
            case "E" -> newC += 1;
            case "SE" -> {
                newR += 1;
                newC += 1;
            }
            case "S" -> newR += 1;
            case "SW" -> {
                newR += 1;
                newC -= 1;
            }
            case "W" -> newC -= 1;
            case "NW" -> {
                newR -= 1;
                newC -= 1;
            }
            default -> throw new Exception("Invalid direction: " + direction);
        }

        if (!inBounds(newR, newC)) {
            throw new Exception("Position is out of bounds!");
        }

        r = newR;
        c = newC;
        revealPosition(r, c);
        changed();
        if (reachedEndPosition(r, c)) {
            gameOver = true;
        }

        if (grid[r][c].isMine()) {
            gameOver = true;
            throw new Exception("Game Over! You have stepped on a Mine!");
        }
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
        cell.setLabel(cell.updateLabel());
    }

    private boolean inBounds(int r, int c) {
        return 0 <= r && r < GRID_DIMENSION && 0 <= c && c < GRID_DIMENSION;
    }

    private boolean reachedEndPosition(int r, int c) {
        return (r == GRID_DIMENSION - 1 && c == GRID_DIMENSION - 1);
    }
}
