package Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Implementes a player whi has Cards and defined actions 
 */
public class Player {
    private final java.util.logging.Logger LOG = Logger.getAnonymousLogger();
    private final PlayerLog CLIENT_LOG = new PlayerLog();


    private ArrayList<ICard> cards = new ArrayList<ICard>();
    private ArrayList<ICmd> actionsList = new ArrayList<ICmd>();
    private String name;

    public void uselessMethod() {
        System.out.println("eh ouais ma gueule, tu kiffes les conflits?");
    }

    public Player(String name) {
        this.name = name;
        LOG.addHandler(CLIENT_LOG);
    }

    public ArrayList<ICmd> getActionsList() {
        return actionsList;
    }

    public ArrayList<ICard> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setPlayerLogComponent(JTextArea component) {
        CLIENT_LOG.setComponent(component);
    }

    public void publish(String s) {
        LOG.info(s);
    }
}
