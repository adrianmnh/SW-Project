package classes.subclasses;

import javax.swing.*;

public class MyImageIcon extends ImageIcon {

    public String name;
    public ImageIcon img;

    public MyImageIcon(String path){
        this.name = path.replace("ui/", "");
        this.img = new ImageIcon(getClass().getClassLoader().getResource(path));
    }

}
