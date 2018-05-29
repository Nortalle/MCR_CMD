import Model.Game;
import Model.Map;
import View.Frame;

public class CMD {

    public static void main(String[] args) {


        Game.getInstance().setMap(new Map(20, 15));

        Frame frame = new Frame(20, 15);

    }
}
