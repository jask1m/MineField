package mineField;

import javax.swing.*;
import mvc.*;

public class MineFieldPanel extends AppPanel{
    private JButton NW, N, NE, W, E, SW, S, SE;


    public MineFieldPanel(AppFactory factory) {
        super(factory);

        JPanel p = new JPanel();

        p = new JPanel();
        NW = new JButton("NW");
        p.add(NW);
        controlPanel.add(p);

        p = new JPanel();
        N = new JButton("N");
        p.add(N);
        controlPanel.add(p);

        p = new JPanel();
        NE = new JButton("NE");
        p.add(NE);
        controlPanel.add(p);

        p = new JPanel();
        W = new JButton("W");
        p.add(W);
        controlPanel.add(p);

        p = new JPanel();
        E = new JButton("E");
        p.add(E);
        controlPanel.add(p);

        p = new JPanel();
        SW = new JButton("SW");
        p.add(SW);
        controlPanel.add(p);

        p = new JPanel();
        S = new JButton("S");
        p.add(S);
        controlPanel.add(p);

        p = new JPanel();
        SE = new JButton("SE");
        p.add(SE);
        controlPanel.add(p);

        setListeners();
    }

    public void setListeners() {
        NW.addActionListener(this);
        N.addActionListener(this);
        NE.addActionListener(this);
        W.addActionListener(this);
        E.addActionListener(this);
        SW.addActionListener(this);
        S.addActionListener(this);
        SE.addActionListener(this);
    }

    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }
}
