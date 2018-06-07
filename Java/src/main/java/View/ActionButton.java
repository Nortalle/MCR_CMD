package View;

import Controler.Controller;
import Model.Action;
import Model.Game;
import Model.ICmd;
import Model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButton extends JButton {

    Action action;

    public ActionButton(final Player player, final Action action) {

        this.action = action;
        setText(action.toString());

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.getInstance().addCommandToPlayer(player, action.createCommand());
                Controller.getInstance().getFrame().update();
            }
        });
    }
}
