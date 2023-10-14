package classes.ui;

import panels.MainFrame;

import javax.swing.*;
import java.awt.*;

public class UIRoundButton extends JButton {

    MainFrame parentFrame;

    public UIRoundButton() {
        super();
        setUp();
    }
    public UIRoundButton(String text) {
        super(text);
        setUp();
    }

    public UIRoundButton(String text, MainFrame parentFrame) {
        super(text);
        setUp();
        this.parentFrame = parentFrame;
        setForeground(Color.ORANGE);
        setBackground(parentFrame.baseRed);
    }

    private void setUp(){
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setFocusPainted(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(parentFrame.baseRed);
        } else {
            g.setColor(getBackground().brighter());
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

        UIRoundButton button = new UIRoundButton("Click me");
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);

        frame.add(button);
        frame.setVisible(true);
    }
}
