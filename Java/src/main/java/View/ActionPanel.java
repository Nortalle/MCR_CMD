package View;

import Model.*;
import Model.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ActionPanel extends JPanel {

    JComboBox cardJComboBox = new JComboBox();
    JPanel contentPanel = new JPanel();
    JPanel actionsPanel = new JPanel();
    JPanel selectedActionsPanel = new JPanel();
    ArrayList<String> selectedActions = new ArrayList<>();

    Player player;

    public ActionPanel(String title) {

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        selectedActionsPanel.setLayout(new BoxLayout(selectedActionsPanel, BoxLayout.Y_AXIS));
        //actionsPanel.setBackground(Color.GREEN);
        //selectedActionsPanel.setBackground(Color.BLUE);
        contentPanel.add(new Label(title));
        contentPanel.add(cardJComboBox);
        contentPanel.add(actionsPanel);
        contentPanel.add(selectedActionsPanel);

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

        for(Action action : card.getActions()){
            actionsPanel.add(new ActionButton(player, action));
        }

        actionsPanel.revalidate();
        actionsPanel.repaint();
    }

    private void displaySelectedActions(){
        selectedActionsPanel.removeAll();
        for(ICmd action: player.getActionsList()){
            selectedActionsPanel.add(new JLabel(action.toString()));
        }
        selectedActionsPanel.revalidate();
    }

    public void update() {
        displayActions();
        displaySelectedActions();
    }
}
