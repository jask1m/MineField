package mineField;

import mvc.Command;
import mvc.Model;

public class MoveCommand extends Command {
    Heading heading;

    public MoveCommand(Model model, String direction) {
        super(model);
        heading = Heading.valueOf(direction);

    }

    @Override
    public void execute() throws Exception {
        MineField mineField = (MineField) model;
        mineField.move(heading.name());
    }
}
