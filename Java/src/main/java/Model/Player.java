package Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Implementes a player who has Cards and defined actions
 */
public class Player {
    private final java.util.logging.Logger LOG = Logger.getAnonymousLogger();
    private final PlayerLog CLIENT_LOG = new PlayerLog();


    private ArrayList<ICard> cards = new ArrayList<ICard>();
    private ArrayList<ICmd> actionsList = new ArrayList<ICmd>();
    private String name;

    /**
     * Constructor of the player
     * @param name, String, name of the player
     */
    public Player(String name) {
        this.name = name;
        LOG.addHandler(CLIENT_LOG);
    }

    /**
     * @return ArrayList<ICmd> list of the actions of the player
     */
    public ArrayList<ICmd> getActionsList() {
        return actionsList;
    }

    /**
     * @return ArrayList<ICard> list of the cards of the player
     */
    public ArrayList<ICard> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Set logs
     * @param component
     */
    public void setPlayerLogComponent(JTextArea component) {
        CLIENT_LOG.setComponent(component);
    }

    /**
     * Show infs about the player
     * @param s info
     */
    public void publish(String s) {
        LOG.info(s);
    }
}
