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
        return new String[] { };
    }
    public String about() {
        return "MineField. Copyright 2025 by Group 6";
    }
}
