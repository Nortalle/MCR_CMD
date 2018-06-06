package View;

import Model.*;
import Model.units.FakeUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class Frame {

    static final int PANEL_LEFT     = 1;
    static final int PANEL_RIGHT    = 2;


    private Grid grid;
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

        updateCards(PANEL_LEFT, Game.getInstance().getPlayerOne());
        updateCards(PANEL_RIGHT, Game.getInstance().getPlayerTwo());

        final JButton update = new JButton("UPDATE");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        guiFrame.getContentPane().add(update, BorderLayout.SOUTH);

        generateMap(columns,rows);
        guiFrame.setVisible(true);
    }

    public void repaintAll(){
        guiFrame.getContentPane().repaint();
    }

    public void update(){
        grid.update();
        panelLeft.update();
        panelRight.update();
    }

    public void updateCards(int side, Player player) {

        ActionPanel panel = side == PANEL_LEFT ? panelLeft : panelRight;
        panel.setPlayer(player);
    }

    public JFrame getGuiFrame() {
        return guiFrame;
    }

    public Grid getGrid() {
        return grid;
    }

    private void generateMap(int columns, int rows){

        final int MAX_TREE = 20;
        final int MAX_ROCK = 20;

        Random rand = new Random();

        int nbrTrees = rand.nextInt(MAX_TREE) + 1;
        int nbrRocks = rand.nextInt(MAX_ROCK) + 1;

        for(int i = 0; i < nbrTrees; i++){
            new FakeUnit(Game.getInstance().getMap().getCell(rand.nextInt(columns), rand.nextInt(rows)));
        }
    }
}
