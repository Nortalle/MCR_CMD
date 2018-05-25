package View;

import Model.Action;

import javax.swing.JPanel;
import java.awt.*;
import java.util.List;

public class ActionPanel extends JPanel {

    List<Action> actions;

    public ActionPanel(String title) {

        add(new Label(title));
    }

    public void provideActions(List<Action> actions) {

        //Clear previous state
        removeAll();
        revalidate();
        repaint();

        this.actions = actions;
        for(Action action : actions)
            add(new ActionButton(action));
    }
}
