package classes.subclasses;

import runes.Rune;

import java.util.List;
import java.util.Map;
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
        for(Map.Entry<MonsterImageIcon, RuneSet> entry : this.entrySet()){
            if(entry.getKey().summonId == summonId){
                RuneSet runeSet = entry.getValue();
                return runeSet;
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

        for(Map.Entry<MonsterImageIcon, RuneSet> entry : this.entrySet()){
            int id = entry.getKey().summonId;
            sb.append("MonsterImageIcon " + id);
            sb.append(" - {");
            sb.append(entry.getValue() + " " );
            sb.append("\t}\n");
        }
        sb.append("}");
        return sb.toString();
    }

    public void printEntries(){
        System.out.println("KeySet: ");
        for(Map.Entry<MonsterImageIcon, RuneSet> entry : this.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    public boolean runeIn(int summonId, Rune r){
        return this.getBySummonId(summonId).contains(r);
    }
}
