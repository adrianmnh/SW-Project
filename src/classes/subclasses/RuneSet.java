package classes.subclasses;

import runes.Rune;

import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class RuneSet extends TreeSet<Rune> {
//public class RuneSet extends HashSet<Rune> {
    public RuneSet(){
        super(Comparator.comparingInt(Rune::getPosInt));
//        super();
    }

    //Sort the Runes by rune Position



    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(this.size() != 0) {
            sb.append("\tRuneSet: ");
            for(Rune r : this){
                sb.append(r.runeId + " ");
            }
        } else {
            sb.append("\tRuneSet: empty");
        }
        return sb.toString();
    }

    public boolean runeIn(Rune r){
        return this.contains(r);
    }
}
