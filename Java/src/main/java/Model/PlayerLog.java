package Model;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class PlayerLog extends Handler {
    private JTextArea component;

    @Override
    public void publish(LogRecord record) {
        component.setText(record.getMessage());
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}

    public void setComponent(JTextArea component) {
        this.component = component;
    }
}
