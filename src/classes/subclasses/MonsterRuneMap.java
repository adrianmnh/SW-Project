package classes.subclasses;

import runes.Rune;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonsterRuneMap extends TreeMap<MonsterImageIcon, RuneSet> {
    public MonsterRuneMap(){
        super();

    }

    public void addSummon(MonsterImageIcon monster){
        RuneSet runeSet = new RuneSet();
        this.put(monster, runeSet);
        System.out.println("MonsterRuneMap: addSummon: " + monster.summonId + " new RuneSet->>>>" + runeSet);
    }

    public void addSummon(MonsterImageIcon monster, RuneSet runeSet){
        this.put(monster, runeSet);
    }

    public RuneSet getBySummonId(int summonId){
        for(MonsterImageIcon monster : this.keySet()){
            if(monster.summonId == summonId){
                return this.get(monster);
            }
        }
        return null;
    }

    public List<MonsterImageIcon> getSortedByBaseID(){
        //sort by baseId
        Stream<MonsterImageIcon> sorted = this.keySet().stream().sorted((o1, o2) -> {
            if(o1.baseId > o2.baseId){
                return 1;
            }else if(o1.baseId < o2.baseId){
                return -1;
            }else{
                return 0;
            }
        });


        return sorted.collect(Collectors.toList());
    }

    public String toString(){
        System.out.println("RuneMap:\n{");
        StringBuilder sb = new StringBuilder();

        for(MonsterImageIcon monsterImageIcon : this.keySet()){
            int id = monsterImageIcon.summonId;
            sb.append("MonsterImageIcon " + monsterImageIcon.monster.getSummonId());
            sb.append(" - {");
            sb.append(this.get(monsterImageIcon) + " " );
            sb.append("\t}\n");
        }
        sb.append("}");
        return sb.toString();
    }

    public void printKeySet(){
        System.out.println("KeySet: ");
        for(MonsterImageIcon monsterImageIcon : this.keySet()){
            System.out.println(monsterImageIcon);
            System.out.println(this.get(monsterImageIcon));
        }
    }

    public boolean runeIn(int summonId, Rune r){
        return this.getBySummonId(summonId).contains(r);
    }
}
