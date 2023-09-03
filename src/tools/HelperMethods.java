package tools;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.ResultSet;

public class HelperMethods {

    public static void processResultSet(ResultSet rs) {


    }

    public static ImageIcon getImageIcon(String path){
        return new ImageIcon(HelperMethods.class.getClassLoader().getResource(path));
    }

    public static void printCurrentClassName(){
        StackTraceElement t = Thread.currentThread().getStackTrace()[2];
        System.out.println(t.getClassName().replace("classes.", "::::")+":::"+t.getMethodName());
    }

    public static void resizeComponent(Component jp, int width, int height){
        jp.setMinimumSize(new Dimension(width, height));
        jp.setPreferredSize(new Dimension(width, height));
        jp.setMaximumSize(jp.getPreferredSize());
        jp.setMinimumSize(jp.getPreferredSize());
    }

    public static ImageIcon scaleImage(ImageIcon img, int width, int height){
        return new ImageIcon(img.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH));
    }

    public static void padComponent(JLabel comp, int top, int left, int bottom, int right){
        Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
        comp.setBorder(border);
    }


}

