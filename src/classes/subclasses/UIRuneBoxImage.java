package classes.subclasses;

import runes.Rune;

import javax.swing.*;
import java.awt.*;

public class RuneBoxImage extends JLabel {
    public Rune rune;
    public RuneBoxImage(Rune rune){
        super();
        this.rune = rune;
    }
    public RuneBoxImage(Rune rune, ImageIcon imageIcon){
        super(imageIcon);
        this.rune = rune;
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setForeground(Color.ORANGE);
        this.setFont(this.getFont().deriveFont(14f));
        this.updateDisplay();
    }

    public void updateDisplay(){
        //        String alias = rune.summonAlias == null ? "" : rune.summonAlias;
//        this.setText(alias);
        String summonId = rune.summonId == -1 ? "" : rune.summonId + "";
        this.setText(rune.runeId + ":" + summonId);
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
