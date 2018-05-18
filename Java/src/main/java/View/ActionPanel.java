package View;

import Model.Action;

import javax.swing.JPanel;
import java.awt.*;
import java.util.List;

public class ActionPanel extends JPanel {

    boolean initialized = false;

    public ActionPanel(String title) {

        add(new Label(title));
    }

    public void provideActions(List<Action> actions) {

        if(initialized)
            throw new RuntimeException("Action panel already initialized");

        for(Action action : actions)
            add(new ActionButton(action));

        initialized = true;
    }
}
