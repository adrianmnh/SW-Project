package runes;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Rune implements Comparable<Rune> {

    private RuneGrade runeGrade;
    private RuneSet runeSet;
    public RunePosition runePosition;
    private boolean runeInnate;
    private MainStat mainStat;
    public ArrayList<SubStat> subs;
    public boolean isEquipped;

    /************************************** Constructors **************************************/

    public Rune(String grade, String set, String pos, String innate, String stat){
        setRuneGrade(grade);
        setRuneSet(set);
        setRunePosition(pos);
        setRuneInnate(innate);
        setRuneMainStat(stat, grade);
        subs = new ArrayList<>();
        this.isEquipped = false;
    }
    public Rune(String longstring){
        System.out.println(longstring);
        String[] arr;
        arr = longstring.split(" ");
//        System.out.println(Arrays.toString(arr));
        setRuneGrade(arr[0]);
        setRuneSet(arr[1]);
        setRunePosition(arr[2]);
        setRuneInnate(arr[3]);
        setRuneMainStat(arr[4], arr[0]);
        subs = new ArrayList<>();
        if(this.getRuneInnate()){
            addRuneSubstat(arr[13], arr[14]);
            addRuneSubstat(arr[5], arr[6]);
            addRuneSubstat(arr[7], arr[8]);
            addRuneSubstat(arr[9], arr[10]);
            addRuneSubstat(arr[11], arr[12]);
        }else{
            addRuneSubstat(arr[5], arr[6]);
            addRuneSubstat(arr[7], arr[8]);
            addRuneSubstat(arr[9], arr[10]);
            addRuneSubstat(arr[11], arr[12]);
        }
        this.isEquipped = false;
        System.out.println("********************Rune created********************");
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.getGradeString() +" ");
        System.out.println("Grade");
        s.append(this.getSetString() +" ");
        System.out.println("Set");
        s.append(this.getPositionString() +" ");
        System.out.println("Pos");
        s.append(this.getInnateString() +" ");
        System.out.println("Innate");
        s.append(this.getMainstatString() +" ");
        System.out.println("Mainstat");
        s.append(this.getSubstatString() +" ");
        System.out.println("Substat");
        return s.toString();
    }
    public String getGradeString(){ return this.getGrade().toString();}
    public String getSetString(){ return this.runeSet.getSet(); }
    public String getPositionString(){return this.getPos().toString();}
    public String getInnateString(){ return this.getRuneInnate() ? "yes" : "no"; }
    public String getMainstatString(){ return this.mainStat.getMainStatAttribute(); }
    public String getSubstatString(){ String s = "";
        System.out.println(subs);
        System.out.println("-------------------------------------------------");
        for(int i = 0; i<this.subs.size(); i++){
            SubStat sub = subs.get(i);
            s += sub.getSubStat() + " " +
                    sub.getSubValue() + " ";
        }
        return s;
    }



    private void setRuneGrade(String x){
        int grade = Integer.parseInt(x);
        if(grade==6) this.runeGrade = RuneGrade.six;
        else this.runeGrade = RuneGrade.five;
    }
    public void setRuneSet(String set){
        switch(set){
            case "Violent": this.runeSet = RuneSet.violent; break;
            case "Swift": this.runeSet = RuneSet.swift; break;
            case "Rage": this.runeSet = RuneSet.rage; break;
            case "Blade": this.runeSet = RuneSet.blade; break;
            case "Will": this.runeSet = RuneSet.will; break;
            case "Nemesis": this.runeSet = RuneSet.nemesis; break;
            default: this.runeSet = null; break;
        }
    }
    private void setRunePosition(String pos){
        int position = Integer.parseInt(pos);
        switch(position){
            case 1: this.runePosition = RunePosition.one;break;
            case 2: this.runePosition = RunePosition.two;break;
            case 3: this.runePosition = RunePosition.three;break;
            case 4: this.runePosition = RunePosition.four;break;
            case 5: this.runePosition = RunePosition.five;break;
            case 6: this.runePosition = RunePosition.six;break;
        }
    }
    private void setRuneInnate(String inn){
        if(Integer.parseInt(inn) == 0) this.runeInnate = false;
        else this.runeInnate = true;

    }
    private void setRuneMainStat(String stat, String grade){
          this.mainStat = new MainStat(stat, grade);
    }
    public void addRuneSubstat(String sub, String val){
        this.subs.add(new SubStat(sub, val));
    }
    public RuneGrade getGrade(){
        return this.runeGrade;
    }

    public MainStat getMainStat(){ return this.mainStat; }

    public RuneSet getSet(){
        return this.runeSet;
    }

    public RunePosition getPos(){
        return this.runePosition;
    }
    public int getPosInt(){
        String s = this.getPositionString();
        return Integer.parseInt(s);
    }
    public boolean getRuneInnate(){
        return this.runeInnate;
    }


    public void addRuneSubstat(SubStat s){
        this.subs.add(s);
    }
    public ArrayList<SubStat> getSubStats(){
        return this.subs;
    }


    public boolean getIsEquipped(){
        return this.isEquipped;
    }

    public void setIsEquipped(boolean b){
        this.isEquipped = b;
    }

    public boolean hasMainStat(String s){
        if(this.getMainstatString().equals(s)) return true;

        return false;
    }

    public boolean hasSubStat(String s){
        for(SubStat sub : this.getSubStats()){
            if(sub.getSubStat().equals(s))return true;
        }
        return false;
    }

    public boolean hasSubStats(String a, String b){
        boolean t1 = this.hasSubStat(a);
        boolean t2 = this.hasSubStat(b);
        if(t1 && t2)return true;
        return false;
    }
    public boolean hasSubStats(String a, String b, String c){
        boolean t1 = this.hasSubStats(a, b);
        boolean t2 = this.hasSubStat(c);
        if(t1 && t2)return true;
        return false;
    }



//    public String toStringDB(){
//        String s =  this.getGradeString() +" " +
//                this.getSetString() + " " +
//                this.getPositionString() + " " +
//                this.getInnateString() + " " +
//                this.getMainstatString() + " ";
//                //this.getSubstatString();
//        if(!this.getRuneInnate()){
//            s += this.subs.remove(0);
//        }
//        return s;
//    }

    public String toStringGUI(){
        StringBuilder b = new StringBuilder();
        //Violent 6*
        //Slot 2 SPD
        //Innate
        b.append(String.format("%s %s ★\nSlot %s %s\n", this.getSet().toString().toUpperCase(),
                this.getGrade(), this.getPos(), this.getMainStat().toString().toUpperCase()));
        if(getRuneInnate()){
            b.append(String.format("%s\n%s\n%s\n%s\n%s",this.getSubStats().get(0),
                    this.getSubStats().get(1),this.getSubStats().get(2),this.getSubStats().get(3)
                    ,this.getSubStats().get(4)).toUpperCase());
        }
        else{
            b.append(String.format("%s\n%s\n%s\n%s",this.getSubStats().get(0),
                    this.getSubStats().get(1),this.getSubStats().get(2),this.getSubStats().get(3)).toUpperCase());
        }

        return b.toString();
    }
    public void printJSON(){
//        StringBuilder s = new StringBuilder();
//        s.append("{\n");
//        //s.append("\"Rune\":\n");
//        //s.append(String.format( "\t\"RuneID\": %s, \n", 14 ));
//        s.append(String.format( "\t\"RuneSet\": \"%s\", \n", this.getSet().getSet() ));
//        s.append(String.format( "\t\"RunePos\": %s, \n", this.getPos().toString() ));
//        s.append(String.format( "\t\"MainStat\": \"%s\", \n", this.getMainStat().getMainStatAttribute()));
//        s.append(String.format( "\t\"Innate\": \"%s\", \n", this.getInnateString() ));
//        s.append(String.format( "\t\"Substats\": [ \n\t\t{\n\t\t  " ));
//        if(getRuneInnate())s.append(String.format( "\t\"%s\": %s, \n\t\t", subs.get(0).getSubStat(), subs.get(0).getSubValue() ));
//        s.append(String.format( "\t\"%s\": %s, \n\t\t", subs.get(1).getSubStat(), subs.get(1).getSubValue()));
//        s.append(String.format( "\t\"%s\": %s, \n\t\t", subs.get(2).getSubStat(), subs.get(2).getSubValue() ));
//        s.append(String.format( "\t\"%s\": %s, \n\t\t", subs.get(3).getSubStat(), subs.get(3).getSubValue() ));
//        s.append(String.format( "\t\"%s\": %s \n\t\t", subs.get(4).getSubStat(), subs.get(4).getSubValue() ));
//        s.append(String.format("}\n\t]"));
//
//        s.append("\n}\n");
//
//        System.out.println(s);
    }


//    public void createRuneFromFile(File f){
//
//    }
//    public void addSubsTEST(){
//
//
//    }

    @Override
    public int compareTo(Rune o) {
//        if(this.runeExists(o)) return 0; // 0 - rune exits
        return -1;
    }
//    public boolean compareAttr(Rune r){
//        if( this.getGradeString().equals(r.getGradeString()) )
//        if( this.getSetString().equals(r.getSetString()) )
//        if( this.getPositionString().equals(r.getPositionString()) )
//        if( this.getInnateString().equals(r.getInnateString()) )
//        if( this.getMainstatString().equals(r.getMainstatString()) )
//        return true;
//        return false;
//    }
//    public boolean compareSubs(Rune r, int i){
//        if( this.getSubStats().get(i).equals(r.getSubStats().get(i))) return true;
//        return false;
//    }

//    public boolean runeExists(Rune r){
//        try{
//            if(this.compareAttr(r))
//            if(this.compareSubs(r, 0) )
//            if(this.compareSubs(r, 1) )
//            if(this.compareSubs(r, 2) )
//            if(this.compareSubs(r, 3) ){
//            if(this.getRuneInnate() == r.getRuneInnate() && this.getRuneInnate() == true){
//                if(this.compareSubs(r, 4) )
//                    return true;
//            }
//            else return true;
//    }
//
//
//        }catch(Exception e){
//            System.out.println("Error found. " + e.getLocalizedMessage());
//        }
//
//
//        return false;
//    }


    public static void main(String[] args) {
        Rune rune = new Rune("5 Violent 3 0 DEF% ATK% 8 ACC 7 SPD 6 CRte 5");
        ArrayList<Rune> bag = new ArrayList<>();
        System.out.println(rune);
        bag.add(rune);
        for(Rune r : bag) {
            System.out.println(r);
        }
    }
}

