package classes.subclasses;

import javax.swing.*;

public class MyImageIcon extends ImageIcon {

    public String name;
    public ImageIcon img;

    public MyImageIcon(String path){
        if(path.contains("ui/"))
            this.name = path.replace("ui/", "");
        else if(path.contains("monsters/")){
            this.name = path.replace("monsters/", "");
            this.name = this.name.replace(".png", "");
            this.name = this.name.replace(".jpg", "");
        }
        this.img = new ImageIcon(getClass().getClassLoader().getResource(path));
    }

}
