package classes.subclasses;

import runes.Rune;

import java.util.ArrayList;

public class RuneBag extends ArrayList <Rune>{
    public RuneBag(){
        super();
    }
    public boolean addRune(Rune r){
        return super.add(r);
    }
    public boolean removeRune(Rune r){
        return super.remove(r);
    }
    public boolean hasRune(Rune r){
        return super.contains(r);
    }
    public Rune getRune(int runeId){
        for(Rune r : this){
            if(r.runeId == runeId) return r;
        }
        return null;
    }
    public Rune getIndex(int index){
        return this.get(index);
    }

}
