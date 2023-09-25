package classes.subclasses;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class RoundButton extends JButton {

    public RoundButton(String text) {
        super(text);
//        setContentAreaFilled(false);
//        setBorderPainted(false);
//        setOpaque(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Round Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        RoundButton button = new RoundButton("Click me");
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);

        frame.add(button);
        frame.setVisible(true);
    }
}
