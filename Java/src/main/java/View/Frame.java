package View;

import Controler.Controller;
import Model.*;
import Model.units.FakeUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Class describing the Frame of the game
 */
public class Frame {

    private static final int PANEL_LEFT     = 1;
    private static final int PANEL_RIGHT    = 2;
    private Grid grid;
    private ActionPanel panelLeft   = new ActionPanel("Joueur 1");
    private ActionPanel panelRight  = new ActionPanel("Joueur 2");

    private JFrame guiFrame = new JFrame();

    /**
     * Constructor of the frame
     * @param columns, int number of columns
     * @param rows, int number of rows
     */
    public Frame(int columns, int rows) {
        Game game = Controller.getInstance().game();

        grid = new Grid(columns, rows);

        guiFrame.setSize(1250,800);
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("CMD MCR");

        guiFrame.getContentPane().add(panelLeft, BorderLayout.LINE_START);
        guiFrame.getContentPane().add(grid, BorderLayout.CENTER);
        guiFrame.getContentPane().add(panelRight, BorderLayout.LINE_END);

        updateCards(PANEL_LEFT, game.getPlayerOne());
        updateCards(PANEL_RIGHT, game.getPlayerTwo());

        final JButton update = new JButton("UPDATE");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        guiFrame.getContentPane().add(update, BorderLayout.SOUTH);

        JButton play = new JButton("PLAY");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().executeAllCommands();
            }
        });
        guiFrame.getContentPane().add(play, BorderLayout.NORTH);



        guiFrame.setVisible(true);
        update();
    }

    /**
     * Repaint the frame
     */
    public void update(){
        grid.update();
        panelLeft.update();
        panelRight.update();
    }

    //Update the display of the cards of a player
    private void updateCards(int side, Player player) {
        ActionPanel panel = side == PANEL_LEFT ? panelLeft : panelRight;
        panel.setPlayer(player);
    }

    /**
     * @return the grid containing the instances of CellView
     */
    public Grid getGrid() {
        return grid;
    }


}
