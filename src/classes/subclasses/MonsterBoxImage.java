package classes.subclasses;

import javax.swing.*;

import java.awt.*;

import static tools.HelperMethods.resizeComponent;
import static tools.HelperMethods.scaleImage;

public class MonsterBoxImage extends JLabel {

    public MonsterImageIcon monsterImageIcon;
    public ImageIcon resized;

    int ICON_DIMENSION;


    public MonsterBoxImage(MonsterImageIcon monsterImageIcon, int dim){
        super();
        this.ICON_DIMENSION = dim;
        this.monsterImageIcon = monsterImageIcon;
        this.resized = scaleImage(monsterImageIcon.imgResource, ICON_DIMENSION-2, ICON_DIMENSION-2);
        this.setIcon(resized);
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        this.setFocusable(false);
//        this.setText(monsterImageIcon.alias);
        this.setText(new String(monsterImageIcon.summonId + ""));
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.BOTTOM);
        resizeComponent(this, ICON_DIMENSION, ICON_DIMENSION+22);
    }

//    @Override
//    public String toString() {
//        String toReturn = ("RuneLabel{\n" +
//                "rune=" + rune.toStringGUI() +
//                '}');
//        return toReturn;
//    }

    public MonsterImageIcon getMonsterImageIcon() {
        return monsterImageIcon;
    }
}
