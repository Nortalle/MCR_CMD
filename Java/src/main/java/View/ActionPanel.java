package View;

import Model.Action;
import Model.ICard;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ActionPanel extends JPanel {

    JComboBox cardJComboBox = new JComboBox();
    JPanel contentPanel = new JPanel();
    JPanel actionsPanel = new JPanel();

    Player player;

    public ActionPanel(String title) {

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
       // actionsPanel.setBackground(Color.GREEN);
        contentPanel.add(new Label(title));
        contentPanel.add(cardJComboBox);
        contentPanel.add(actionsPanel);

        cardJComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                displayActions();
            }
        });

        add(contentPanel);
    }

    public void setPlayer(Player player) {

        this.player = player;
        provideCards(player.getCards());
    }

    public void provideCards(List<ICard> cards) {

        cardJComboBox.removeAll();

        for(ICard card : cards)
            cardJComboBox.addItem(card);

        displayActions();
    }

    private void displayActions() {

        actionsPanel.removeAll();
        ICard card = (ICard)cardJComboBox.getSelectedItem();

        for(Action action : card.getActions())
            actionsPanel.add(new ActionButton(player, action));

        actionsPanel.revalidate();
        actionsPanel.repaint();
    }
}
