import Controler.Controller;

/**
 * Class used to launch the game
 */
public class CMD {

    public static void main(String[] args) {
        //Launching the game
        Controller controllerGame = Controller.getInstance();

        controllerGame.startGame();
    }
}