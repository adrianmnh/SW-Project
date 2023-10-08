package classes.subclasses;

import javax.swing.*;

public class CenteredLabel extends JLabel {
    public CenteredLabel(String text){
        super(text);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public CenteredLabel(ImageIcon imageIcon){
        super(imageIcon);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public CenteredLabel(String text, int horizontalAlignment){
        super(text, horizontalAlignment);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public CenteredLabel(Icon image){
        super(image);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public CenteredLabel(Icon image, int horizontalAlignment){
        super(image, horizontalAlignment);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public CenteredLabel(){
        super();
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
