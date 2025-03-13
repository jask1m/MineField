package mvc;

import tools.Subscriber;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class View extends JPanel implements Subscriber {
    protected Model model;

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        setBorder(blackLine);
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void update() {
        repaint();
    }

    public void setModel(Model newModel) {
        model.unsubscribe(this);
        model = newModel;
        model.subscribe(this);
        repaint();
    }
}