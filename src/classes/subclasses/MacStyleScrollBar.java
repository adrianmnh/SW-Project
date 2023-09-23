package classes.subclasses;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class MacStyleScrollBar extends JScrollBar {

    public MacStyleScrollBar(int orientation) {
        super(orientation);
        setUI(new MacStyleScrollBarUI());
    }
}

