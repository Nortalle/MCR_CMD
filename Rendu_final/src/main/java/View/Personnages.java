package View;

import Model.Player;
import Model.Unit;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Personnages {


    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JProgressBar progressBar3;
    private JProgressBar progressBar4;
    private JPanel main_panel;
    private Player player;

    public Personnages(Player player) {
        this.player = player;





        progressBar1.setMaximum(((Unit)player.getCards().get(0)).getHpMax());
        progressBar2.setMaximum(((Unit)player.getCards().get(1)).getHpMax());
        progressBar3.setMaximum(((Unit)player.getCards().get(2)).getHpMax());
        progressBar4.setMaximum(((Unit)player.getCards().get(3)).getHpMax());


        progressBar1.setValue(((Unit)player.getCards().get(0)).getHp());
        progressBar2.setValue(((Unit)player.getCards().get(1)).getHp());
        progressBar3.setValue(((Unit)player.getCards().get(2)).getHp());
        progressBar4.setValue(((Unit)player.getCards().get(3)).getHp());
    }

    public void update(){
        progressBar1.setValue(((Unit)player.getCards().get(0)).getHp());
        progressBar2.setValue(((Unit)player.getCards().get(1)).getHp());
        progressBar3.setValue(((Unit)player.getCards().get(2)).getHp());
        progressBar4.setValue(((Unit)player.getCards().get(3)).getHp());
        main_panel.repaint();
    }




    public JPanel getMain_panel() {
        return main_panel;
    }
}
