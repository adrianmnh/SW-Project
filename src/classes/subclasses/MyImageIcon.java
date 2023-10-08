package classes.subclasses;

import javax.swing.*;

public class MyImageIcon extends ImageIcon {

    public String imgName;
    public ImageIcon imgResource;

    public String imgPath;

    public MyImageIcon(){
        super();
    }


    public MyImageIcon(String imgPath){
        this.imgPath = imgPath;

        if(imgPath.contains("ui/"))
            this.imgName = imgPath.replace("ui/", "");
        else if(imgPath.contains("monsters/")){
            this.imgName = imgPath.replace("monsters/", "");
            this.imgName = this.imgName.replace(".png", "");
            this.imgName = this.imgName.replace(".jpg", "");
        }
        this.imgResource = new ImageIcon(getClass().getClassLoader().getResource(imgPath));
    }

    public String toString(){
        return "MyImageIcon{" +
                "imgPath='" + imgPath + '\'' +
                "imgName='" + imgName + '\'' +
                '}';
    }

    public ImageIcon getImageIcon(){
        return imgResource;
    }

}
