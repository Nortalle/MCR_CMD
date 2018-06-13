package Model;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * A player's log which is a Handler which define how the
 * given information will be displayed
 */
public class PlayerLog extends Handler {
    private JTextArea component;

    @Override
    public void publish(LogRecord record) {


        component.setText(record.getMessage());
        if (record.getMessage().startsWith("ERROR : "))
            component.setForeground(Color.RED);
        else{
            component.setForeground(Color.GREEN);
        }

    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }

    /**
     * sets the component where the log are going to be written
     *
     * @param component : component where the log are going to be written
     */
    public void setComponent(JTextArea component) {
        this.component = component;
    }
}
