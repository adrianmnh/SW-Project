package classes.subclasses;

import javax.swing.*;

public class MacStyleScrollBar extends JScrollBar {

    public MacStyleScrollBar(int orientation) {
        super(orientation);
        setUI(new MacStyleScrollBarUI());
    }
}

