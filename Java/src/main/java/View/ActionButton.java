package View;

import Model.Action;

import javax.swing.*;

public class ActionButton extends JButton {

    Action action;

    public ActionButton(Action action) {

        this.action = action;
        setText(action.toString());
    }
}
