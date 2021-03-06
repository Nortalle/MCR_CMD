package View;

import Model.*;
import Model.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Class representing the action panel
 */
public class ActionPanel extends JPanel {

    private JComboBox cardJComboBox = new JComboBox();
    private JPanel contentPanel = new JPanel();
    private JPanel actionsPanel = new JPanel();
    private JPanel selectedActionsPanel = new JPanel();
    private JPanel cachePanel = new JPanel();
    private JPanel lifePanel = new JPanel();
    private JPanel logsPanel = new JPanel();
    private Personnages lifeBars;
    private Logs logs;

    private Player player;

    /**
     * Constructor of the ActionPanel
     * @param title
     */
    public ActionPanel(String title) {

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        selectedActionsPanel.setLayout(new BoxLayout(selectedActionsPanel, BoxLayout.Y_AXIS));
        cachePanel.setLayout(new BoxLayout(cachePanel, BoxLayout.Y_AXIS));
        lifePanel.setLayout(new BoxLayout(lifePanel, BoxLayout.Y_AXIS));
        logsPanel.setLayout(new BoxLayout(logsPanel, BoxLayout.Y_AXIS));
        contentPanel.add(new Label(title));
        contentPanel.add(cardJComboBox);
        contentPanel.add(actionsPanel);
        JButton cache = new JButton("Cache/révèle actions");

        cache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySelectedActions();
                selectedActionsPanel.setVisible(!selectedActionsPanel.isVisible());
                cache.setText("Actions visible : " + selectedActionsPanel.isVisible());
                update();
            }
        });

        cache.setLayout(new BoxLayout(cache, BoxLayout.Y_AXIS));
        cachePanel.add(cache, BorderLayout.SOUTH);

        contentPanel.add(selectedActionsPanel);

        contentPanel.add(cachePanel, BorderLayout.SOUTH);
        cardJComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                displayActions();
                update();
            }
        });

        add(contentPanel);
    }

    /**
     * Set the player configuration
     * @param player, Player to set
     */
    public void setPlayer(Player player) {

        this.player = player;

        this.lifeBars = new Personnages(player);
        this.logs = new Logs();

        lifePanel.add(lifeBars.getMain_panel());
        contentPanel.add(lifePanel);

        player.setPlayerLogComponent(logs.getTextArea1());
        logsPanel.add(logs.getMain_panel());

        contentPanel.add(logsPanel);
        provideCards(player.getCards());
    }

    /**
     * Show all the cards of oa liste of cards
     * @param cards list of cards to show
     */
    public void provideCards(List<ICard> cards) {

        cardJComboBox.removeAll();

        for (ICard card : cards)
            cardJComboBox.addItem(card);

        displayActions();
    }

    //put all the actions of a card in the liste of possible actions
    private void displayActions() {
        actionsPanel.removeAll();
        for (ICard card : player.getCards()) {
            card.setSelected(false);
        }

        ICard card = (ICard) cardJComboBox.getSelectedItem();
        card.setSelected(true);


        for (Action action : card.getActions()) {
            actionsPanel.add(new ActionButton(player, action));
        }

        actionsPanel.revalidate();
        actionsPanel.repaint();
    }

    //put all the commands chosen by the player in a list of commands
    private void displaySelectedActions() {
        selectedActionsPanel.removeAll();
        for (ICmd action : player.getActionsList()) {
            selectedActionsPanel.add(new JLabel(action.toString()));
        }
        selectedActionsPanel.revalidate();
    }

    /**
     * Repaint the ActionPanel
     */
    public void update() {
        lifeBars.update();
        displayActions();
        displaySelectedActions();
        repaint();
    }
}
