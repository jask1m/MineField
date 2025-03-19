package mineField;

import mvc.*;

public class MineFieldFactory implements AppFactory {
    public Model makeModel() {
        return new MineField();
    }
    public View makeView(Model m) {
        return new MineFieldView((MineField) m);
    }

    public String[] getEditCommands() {
        return new String[] {"NW", "N", "NE", "W", "E", "SW", "S", "SE"};
    }
    public Command makeEditCommand(Model model, String type, Object source) {
        return new MoveCommand(model, type);
    }
    public String getTitle() {
        return "Mine Field";
    }
    public String[] getHelp() {
        return new String[] {
                "Guide Sargent Rock through a minefield to the bottom-right corner",
                "The mine detector will tell you how many mines are in the neighboring cells.",
                "Use the direction buttons to move Sargent Rock.",
                "NW = North West",
                "N = North",
                "NE = North East",
                "W = West",
                "E = East",
                "SW = South West",
                "S = South",
                "SE = South East"
        };
    }
    public String about() {
        return "MineField. Copyright 2025 by Group 6";
    }
}
