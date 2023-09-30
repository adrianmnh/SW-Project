package classes.subclasses;

import javax.swing.*;

public class MyImageIcon extends ImageIcon {

    public String imgName;
    public ImageIcon imgResource;

    public String path;

    public MyImageIcon(String path){
        this.path = path;

        if(path.contains("ui/"))
            this.imgName = path.replace("ui/", "");
        else if(path.contains("monsters/")){
            this.imgName = path.replace("monsters/", "");
            this.imgName = this.imgName.replace(".png", "");
            this.imgName = this.imgName.replace(".jpg", "");
        }
        this.imgResource = new ImageIcon(getClass().getClassLoader().getResource(path));
    }

    public String toString(){
        return "MyImageIcon{" +
                "imgName='" + imgName + '\'' +
                '}';
    }

}
