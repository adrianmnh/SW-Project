package classes.ui;

import classes.subclasses.MonsterImageIcon;

import javax.swing.*;

import java.awt.*;

import static tools.HelperMethods.resizeComponent;
import static tools.HelperMethods.scaleImage;

public class UIMonsterBoxImage extends JLabel {

    public MonsterImageIcon monsterImageIcon;
    public ImageIcon resized;

    int ICON_DIMENSION;

    int LABEL_DIMENSION;


    public UIMonsterBoxImage(MonsterImageIcon monsterImageIcon, int dim, int labelDim){
        super();
        this.ICON_DIMENSION = dim;
        this.LABEL_DIMENSION = labelDim;
        this.monsterImageIcon = monsterImageIcon;
        this.resized = scaleImage(monsterImageIcon.imgResource, ICON_DIMENSION-2, ICON_DIMENSION-2);
        this.setIcon(resized);
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        this.setFocusable(false);
//        this.setText(new String(monsterImageIcon.summonId + ""));
        this.setText(monsterImageIcon.alias);
//        this.setFont(this.getFont().deriveFont(Font.BOLD, 12f));
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.BOTTOM);
        resizeComponent(this, ICON_DIMENSION, ICON_DIMENSION+LABEL_DIMENSION);
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
