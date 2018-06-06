package View;

import Model.*;
import javafx.beans.Observable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.TimerTask;

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

/*
        final JButton update = new JButton("UPDATE");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        guiFrame.getContentPane().add(update);*/

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
}
