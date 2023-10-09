package classes.subclasses;

import panels.MainFrame;
import runes.Rune;

import javax.swing.*;
import java.awt.*;

import static tools.HelperMethods.resizeComponent;

public class UIRuneBox extends JPanel {

    public UIRuneBoxImage runeBoxImage;
    public UIRuneBoxLabel runeBoxLabel;
    public UIRuneBox(){
        super();
    }
    public UIRuneBox(Rune r, ImageIcon runeAvailable, ImageIcon runeNotAvailable, ImageIcon runeEquipped, MainFrame parentFrame, Color FONT_COLOR, int LEFT_WIDTH, int RIGHT_WIDTH, int ROW_HEIGHT){
        super();
        if(!r.isEquipped){
            this.runeBoxImage = new UIRuneBoxImage(r, runeAvailable);
        } else {
            this.runeBoxImage = new UIRuneBoxImage(r, runeNotAvailable);
        }

        this.runeBoxLabel = new UIRuneBoxLabel(r, FONT_COLOR, RIGHT_WIDTH, ROW_HEIGHT);

        this.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, parentFrame.baseRed));
        this.add(runeBoxImage);
        this.add(runeBoxLabel);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
        resizeComponent(this, LEFT_WIDTH + RIGHT_WIDTH + 5, ROW_HEIGHT );

        updateDisplay();

    }

    public void updateDisplay(){
        this.runeBoxImage.updateDisplay();
//        this.runeBoxLabel.updateDisplay();

    }
}
