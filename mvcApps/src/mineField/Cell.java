package mineField;

import java.io.Serializable;

public class Cell implements Serializable {
    private final boolean isMine;
    private boolean hasBeenMined;
    private String label;
    private final int numMinesNearby;

    public Cell(boolean isMine, boolean hasBeenMined, String label, int numMinesNearby) {
        this.isMine = isMine;
        this.hasBeenMined = hasBeenMined;
        this.label = label;
        this.numMinesNearby = numMinesNearby;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setHasBeenMined(boolean hasBeenMined) {
        this.hasBeenMined = hasBeenMined;
    }

    public boolean getHasBeenMined() {
        return hasBeenMined;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String updateLabel() {
        if (!isMine) {
            return String.valueOf(numMinesNearby);
        }
        return "💣";
    }

    public String getLabel() {
        return label;
    }

    public int getNumMinesNearby() {
        return numMinesNearby;
    }
}
