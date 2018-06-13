package View;

import Controler.Controller;
import Model.Action;
import Model.Game;
import Model.ICmd;
import Model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class Representing an action button
 */
public class ActionButton extends JButton {

    Action action;

    /**
     * Constructor of the action button
     * @param player the Player that is linked to the button
     * @param action the Action linked to the button
     */
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
