package stopLight;

import mvc.*;

import javax.swing.*;
import java.awt.*;

public class ChangeCommand extends Command {
    public ChangeCommand(Model model) {
        super(model);
    }
    @Override
    public void execute() {
        Stoplight light = (Stoplight)model;
        light.change();
    }
}
