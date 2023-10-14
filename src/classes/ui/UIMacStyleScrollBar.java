package classes.ui;

import javax.swing.*;

public class UIMacStyleScrollBar extends JScrollBar {

    public UIMacStyleScrollBar(int orientation) {
        super(orientation);
        setUI(new UIMacStyleScrollBarUI());
    }
}

