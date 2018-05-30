package View;

import Model.Action;
import Model.Cell;
import Model.ICard;
import Model.ICmd;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Frame {

    static final int PANEL_LEFT     = 1;
    static final int PANEL_RIGHT    = 2;


    Grid grid;
    ActionPanel panelLeft   = new ActionPanel("Joueur 1");
    ActionPanel panelRight  = new ActionPanel("Joueur 2");

    JFrame guiFrame = new JFrame();

    public Frame(int columns, int rows) {

        grid = new Grid(columns, rows);

        guiFrame.setSize(1000,600);
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("CMD MCR");

        guiFrame.getContentPane().add(panelLeft, BorderLayout.LINE_START);
        guiFrame.getContentPane().add(grid, BorderLayout.CENTER);
        guiFrame.getContentPane().add(panelRight, BorderLayout.LINE_END);

        final ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return null;
            }

            @Override
            public String toString() {
                return "action de ouf";
            }
        });

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return null;
            }

            @Override
            public String toString() {
                return "action de malade";
            }
        });


        ArrayList<ICard> cards = new ArrayList<ICard>();

        cards.add(new ICard() {
            public ArrayList<Action> getActions() {
                return actions;
            }

            public String desctiption() {
                return "SWAG";
            }

            public boolean Invoke(Cell c) {
                return false;
            }

            public String toString() {
                return "AHAHAHAHAH";
            }
        });

        cards.add(new ICard() {
            public ArrayList<Action> getActions() {
                return actions;
            }

            public String desctiption() {
                return "YOLO";
            }

            public boolean Invoke(Cell c) {
                return false;
            }
            public String toString() {
                return "SBFNDJKFNHDJKL";
            }
        });

        updateCards(PANEL_LEFT, cards);
        updateCards(PANEL_RIGHT, cards);

        guiFrame.setVisible(true);
    }

    public void updateCards(int side, List<ICard> cards) {

        ActionPanel panel = side == PANEL_LEFT ? panelLeft : panelRight;
        panel.provideCards(cards);
    }
}
