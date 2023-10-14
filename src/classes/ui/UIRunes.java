package classes.ui;

import panels.MainFrame;

import javax.swing.*;
import java.awt.*;

public class UIRunes extends JPanel {

    int ROW_HEIGHT;
    MainFrame parentFrame;


    public UIRunes(){
        super();
    }

    public UIRunes(MainFrame parentFrame, int ROW_HEIGHT){
        super();
        this.parentFrame = parentFrame;
        this.ROW_HEIGHT = ROW_HEIGHT;
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBackground(parentFrame.baseColor);
    }



    public UIRunes(int orientation){
        super();
        this.setLayout(new BoxLayout(this, orientation));
    }

}
