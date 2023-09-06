package runes;

public class SubStat {

    private RuneStat runeStat;
    private int value;
    private boolean percent;

    public SubStat(){this.runeStat = null; this.value = 0; this.percent=false;}

    public SubStat(String s, String v){
        setSubStat(s);
        setSubStatValue(v);
        if(this.runeStat == RuneStat.ATK || this.runeStat == RuneStat.DEF || this.runeStat == RuneStat.HP) this.percent = true;
    }
    public void setSubStat(String s){
        this.runeStat = RuneStat.fromString(s);
    }

    public void setSubStatValue(String v){
        this.value = Integer.parseInt(v);
    }

    public String getSubStat(){
        return this.runeStat.toString();
    }
    public String getSubValue(){
        return String.valueOf(value);
    }
    public boolean getPc(){
        return this.percent;
    }
    public int getSubValueInt(){
        return this.value;
    }

    public String toString(){
        if(this.runeStat == RuneStat.ATK ||this.runeStat == RuneStat.DEF ||this.runeStat == RuneStat.HP || this.runeStat == RuneStat.RES || this.runeStat == RuneStat.ACC || this.runeStat == RuneStat.CRte || this.runeStat == RuneStat.CDmg)
            return runeStat.getStat() + ": +" + value + "%";
        else
            return runeStat.getStat() + ": +" + value;
    }

    public boolean equals(SubStat s){
        if(this.getSubStat().equals(s.getSubStat()))
        if(this.getSubValue().equals(s.getSubValue()))return true;
        return false;
    }



}
