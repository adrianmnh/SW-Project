package classes.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIImageButton extends JButton {

    JOptionPane optionPane;
    public UIImageButton(ImageIcon imageIcon, JOptionPane optionPane, int option){
        super(imageIcon);

        this.optionPane = optionPane;

        this.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        this.setOpaque(false); // Make the button non-opaque
        this.setBorder(new EmptyBorder(0, 0, 0, 0)); // Remove the border

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionPane.setValue(option);
            }
        });

    }


}
