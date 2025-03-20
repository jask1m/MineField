package mvc;

import tools.Subscriber;
import tools.Utilities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// AppPanel is the MVC controller
public class AppPanel extends JPanel implements Subscriber, ActionListener  {

    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel;
    private final JFrame frame;
    public static int FRAME_WIDTH = 815;
    public static int FRAME_HEIGHT = 465;

    public AppPanel(AppFactory factory) {
        this.setLayout(new GridLayout(1,2));

        // Initialize fields
        this.factory = factory;
        this.model = factory.makeModel();

        // Set up control panel and add it to the LEFT of the grid layout
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4,2));
        this.add(controlPanel);

        // Create view and add it to the RIGHT of the grid layout
        this.view = factory.makeView(model);
        this.add(view);


        // Subscribe to model changes
        model.subscribe(this);

        // Set up frame
        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }


    public void display() { frame.setVisible(true); }

    public void update() {  /* override in extensions if needed */ }

    public Model getModel() { return model; }

    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        // view must also unsubscribe then resubscribe:
        view.setModel(this.model);
        model.changed();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String cmmd = ae.getActionCommand();

            switch (cmmd) {
                case "Save" -> Utilities.save(model, false);
                case "SaveAs" -> Utilities.save(model, true);
                case "Open" -> {
                    Model newModel = Utilities.open(model);
                    if (newModel != null) setModel(newModel);
                }
                case "New" -> {
                    Utilities.saveChanges(model);
                    setModel(factory.makeModel());
                    // needed cuz setModel sets to true:
                    model.setUnsavedChanges(false);
                }
                case "Quit" -> {
                    Utilities.saveChanges(model);
                    System.exit(0);
                }
                case "About" -> Utilities.inform(factory.about());
                case "Help" -> Utilities.inform(factory.getHelp());
                default -> {
                    // handle edit commands using factory
                    Command command = factory.makeEditCommand(model, ae.getActionCommand(), ae.getSource());
                    if (command != null) {
                        command.execute();
                    }
                }
            }
        } catch (Exception e) {
            Utilities.error(e);
        }
    }
}
