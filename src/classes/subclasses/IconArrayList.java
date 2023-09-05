package classes.subclasses;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class IconArrayList<MyImageIcon> extends ArrayList<MyImageIcon> implements Iterable<MyImageIcon> {

    public IconArrayList() {
        super();
    }

    public void add(String name, String path) {
        classes.subclasses.MyImageIcon myImage = new classes.subclasses.MyImageIcon(path);
        super.add((MyImageIcon) myImage);
    }

    public ImageIcon getImage(String name) {
        for (int i = 0; i < super.size(); i++) {
            if (((classes.subclasses.MyImageIcon) super.get(i)).name.equals(name)) {
                return ((classes.subclasses.MyImageIcon) super.get(i)).img;
            }
        }
        return null;
    }

    public MyImageIcon get(String name) {
        for (int i = 0; i < super.size(); i++) {
            if (((classes.subclasses.MyImageIcon) super.get(i)).name.equals(name)) {
                return (MyImageIcon) ((classes.subclasses.MyImageIcon) super.get(i));
            }
        }
        return null;
    }


    @Override
    public Iterator<MyImageIcon> iterator() {
        return super.iterator();
    }
}
