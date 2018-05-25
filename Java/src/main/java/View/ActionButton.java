package View;

import Model.Action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButton extends JButton {

    Action action;

    public ActionButton(final Action action) {

        this.action = action;
        setText(action.toString());

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                action.createCommand();
            }
        });
    }
}
