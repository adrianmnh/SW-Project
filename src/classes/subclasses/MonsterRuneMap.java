package classes.subclasses;

import classes.Monster;
import runes.Rune;

import java.util.HashMap;
import java.util.Set;

public class MonsterRuneMap extends HashMap<Monster, Set<Rune>> {
    public MonsterRuneMap(){
        super();
    }
//    @Override
    public Set<Rune> get(int key){
        for(Monster monster : this.keySet()){
            if(monster.getBaseId() == key){
                return this.get(monster);
            }
        }
        return null;
    }



    public String toString(){
        System.out.println("RuneMap:");
        StringBuilder stringBuilder = new StringBuilder();

        for(Monster monster : this.keySet()){
            int id = monster.getBaseId();
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
