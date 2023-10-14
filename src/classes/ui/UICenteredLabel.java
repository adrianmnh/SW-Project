package classes.ui;

import javax.swing.*;

public class UICenteredLabel extends JLabel {
    public UICenteredLabel(String text){
        super(text);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public UICenteredLabel(ImageIcon imageIcon){
        super(imageIcon);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public UICenteredLabel(String text, int horizontalAlignment){
        super(text, horizontalAlignment);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public UICenteredLabel(Icon image){
        super(image);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public UICenteredLabel(Icon image, int horizontalAlignment){
        super(image, horizontalAlignment);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public UICenteredLabel(){
        super();
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
