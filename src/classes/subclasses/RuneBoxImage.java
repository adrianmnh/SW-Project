package classes.subclasses;

import runes.Rune;

import javax.swing.*;

public class RuneBoxImage extends JLabel {
    public Rune rune;
    public RuneBoxImage(Rune rune){
        super();
        this.rune = rune;
    }
    public RuneBoxImage(Rune rune, ImageIcon imageIcon){
        super(imageIcon);
        this.rune = rune;
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
