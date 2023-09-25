package classes.subclasses;

import runes.Rune;

import java.util.HashMap;
import java.util.Set;

public class MonsterRuneMap extends HashMap<Integer, Set<Rune>> {
    public MonsterRuneMap(){
        super();
    }



    public String toString(){
        System.out.println("RuneMap:");
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer id : this.keySet()){
            stringBuilder.append("MonsterId: " + id + " - {");
            for(Rune r : this.get(id)){
                stringBuilder.append(r.runeId + " ");
            }
            stringBuilder.append("}\n");
        }
        return stringBuilder.toString();
    }

    public boolean runeIn(int monsterId, Rune r){
        return this.get(monsterId).contains(r);
    }
}
