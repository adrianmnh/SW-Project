package classes.subclasses;

import runes.Rune;

import javax.swing.*;
import java.awt.*;

public class UIRuneBoxImage extends JLabel {
    public Rune rune;
    public UIRuneBoxImage(Rune rune){
        super();
        this.rune = rune;
    }
    public UIRuneBoxImage(Rune rune, ImageIcon imageIcon){
        super(imageIcon);
        this.rune = rune;
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setFont(this.getFont().deriveFont(Font.BOLD, 18f));
        this.updateDisplay();
    }

    public void updateDisplay(){
//        String alias = rune.summonAlias == null ? "" : rune.summonAlias;
//        this.setText(alias);
        String summonId = rune.summonId == -1 ? "" : rune.summonId + "";
        this.setText(rune.runeId + ":" + summonId);
        if(rune.isEquipped){
            this.setForeground(Color.ORANGE);
        } else {
            this.setForeground(Color.BLACK);
        }
    }

    @Override
    public String toString() {
        String toReturn = ("RuneLabel{\n" +
                "rune=" + rune.toStringGUI() +
                '}');
        return toReturn;
    }

    public Rune getRune() {
         return rune;
    }
}
