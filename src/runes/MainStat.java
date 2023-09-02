package runes;

public class MainStat{

    private RuneStat mainstat;
    private int value;
    private RuneGrade runeGrade;
    private boolean percent;

    public MainStat(String stringStat, String runeStatGrade){
//        System.out.println("MainStat constructor called");
        setMainStatGrade(runeStatGrade);
        setMainStatAttribute(stringStat);
        setMainStatValue(runeStatGrade, stringStat);
    }
    public void setMainStatGrade(String s){
        if(Integer.parseInt(s)==6)this.runeGrade = RuneGrade.six;
        else this.runeGrade = RuneGrade.five;
    }
    public void setMainStatAttribute(String s){
        this.mainstat = RuneStat.fromString(s);

    }
    private void setMainStatValue(String runeGradeString, String stringStat){
//        System.out.println("setMainStatValue called");
        int runeGrade = Integer.parseInt(runeGradeString);
        if(runeGrade == 6){
            switch (mainstat){
                case SPD:
                    this.value = 42; break;
                case atk, def:
                    this.value = 160; break;
                case hp:
                    this.value = 2448; break;
                case ACC, RES:
                    this.value = 64; break;
                case CRte:
                    this.value = 58; break;
                case CDmg:
                    this.value = 80; break;
                case HP, DEF, ATK:
                    this.value = 63; this.percent = true; break;
            }
        }
        else{
            switch (mainstat){
                case SPD:
                    this.value = 39; break;
                case atk, def:
                    this.value = 135; break;
                case hp:
                    this.value = 2088; break;
                case ACC, RES:
                    this.value = 51; break;
                case CRte:
                    this.value = 47; break;
                case CDmg:
                    this.value = 65; break;
                case HP, DEF, ATK:
                    this.value = 51; this.percent = true; break;
            }
        }
    }

    public RuneStat getGrade(){return this.mainstat;}
    public int getValue(){return this.value;}
    public String getMainStatAttribute(){return this.mainstat.toString();}
    public boolean getPc(){
        return this.percent;
    }

    public String toString(){
        String toReturn = "";
        if(this.percent == true) toReturn = mainstat + ": " + value + "%";
        else if(this.percent == false)toReturn = mainstat + ": " + value;
        return toReturn;
    }

    public static void main(String[] args) {
        MainStat a = new MainStat("ATK%", "6");
        System.out.println(a);
    }
}
