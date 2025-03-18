package mineField;

import java.awt.*;
import javax.swing.*;
import mvc.*;

public class MineFieldPanel extends AppPanel{
    private JButton NW, N, NE, W, E, SW, S, SE;

    public MineFieldPanel(AppFactory factory) {
        super(factory);
        NW = new JButton("NW");
        NW.addActionListener(this);
        controlPanel.add(NW);

        N = new JButton("N");
        N.addActionListener(this);
        controlPanel.add(N);

        NE = new JButton("NE");
        NE.addActionListener(this);
        controlPanel.add(NE);

        W = new JButton("W");
        W.addActionListener(this);
        controlPanel.add(W);

        E = new JButton("E");
        E.addActionListener(this);
        controlPanel.add(E);

        SW = new JButton("SW");
        SW.addActionListener(this);
        controlPanel.add(SW);

        S = new JButton("S");
        S.addActionListener(this);
        controlPanel.add(S);

        SE = new JButton("SE");
        SE.addActionListener(this);
        controlPanel.add(SE);
    }

    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }


}
