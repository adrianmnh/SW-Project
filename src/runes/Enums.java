package runes;

enum RuneStat {
    ATK("ATK%"), HP("HP%"), DEF("DEF%"), SPD("SPD"), CRte("CRte"), CDmg("CDmg"),
     atk("ATK"),  hp("HP"),  def("DEF"), ACC("ACC"), RES("RES");
//    ATK, HP, DEF, SPD, CRte, CDmg,
//    atk, hp, def, ACC, RES;

    private String runeStat;
//
    RuneStat(String stat){ this.runeStat = stat; }
//    public String toString(){ return this.toString(); }
    public String getStat(){ return this.runeStat; }
    public static RuneStat fromString(String s) {
        switch (s){
            case "SPD":
                return RuneStat.SPD;
            case "HP%":
                return RuneStat.HP;
            case "HP":
                return RuneStat.hp;
            case "ATK%":
                return RuneStat.ATK;
            case "ATK":
                return RuneStat.atk;
            case "DEF%":
                return RuneStat.DEF;
            case "DEF":
                return RuneStat.def;
            case "CRte":
                return RuneStat.CRte;
            case "CDmg":
                return RuneStat.CDmg;
            case "ACC":
                return RuneStat.ACC;
            case "RES":
                return RuneStat.RES;
            default:
                return null;
        }
    }
}

enum RuneSet {
//    violent, swift, rage, blade, will,nemesis;
    violent("Violent"), swift("Swift"), rage("Rage"), blade("Blade"), will("Will"),nemesis("Nemesis");
    private String runeSet;
    RuneSet(final String runeSet) { this.runeSet = runeSet; }
    public String getSet(){ return this.runeSet;
    }
}

enum RuneGrade {
    five("5"), six("6");
    private String runeGrade;
    RuneGrade(String grade){
        this.runeGrade = grade;
    }
    public String toString(){ return this.runeGrade; }
}

enum RunePosition {
    one("1"), two("2"), three("3"), four("4"), five("5"), six("6");
    private String runePosition;
    RunePosition(String position){ this.runePosition = position; }
    public String toString(){ return this.runePosition; }
}

