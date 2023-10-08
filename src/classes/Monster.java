package classes;

import database.MonsterDB;

import java.util.ArrayList;

public class Monster {

    private int baseId;
    private String name;
    private int HP;
    private int ATK;
    private int DEF;
    private int SPD;
    private int CRte;
    private int CDmg;
    private int RES;
    private int ACC;
    private int summonId;

    private String alias;


    public Monster(){
        this.summonId = -1;
        this.baseId = 0;
        this.name = "";
        this.HP = 0;
        this.ATK = 0;
        this.DEF = 0;
        this.SPD = 0;
        this.CRte = 0;
        this.CDmg = 0;
        this.RES = 0;
        this.ACC = 0;
    }
    public Monster(int baseId, String name, int HP, int ATK, int DEF, int SPD, int CRte, int CDmg, int RES, int ACC){
        setSummonId(-1);
        this.baseId = baseId;
        this.name = name;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.SPD = SPD;
        this.CRte = CRte;
        this.CDmg = CDmg;
        this.RES = RES;
        this.ACC = ACC;
        this.alias = this.name;
    }
    public Monster(String longstring){
        String[] arr;
        arr = longstring.split(" ");
        setSummonId(-1);
        setBaseId(Integer.parseInt(arr[0]));
        setName(arr[1]);
        setHP(Integer.parseInt(arr[2]));
        setATK(Integer.parseInt(arr[3]));
        setDEF(Integer.parseInt(arr[4]));
        setSPD(Integer.parseInt(arr[5]));
        setCR(Integer.parseInt(arr[6]));
        setCD(Integer.parseInt(arr[7]));
        setRES(Integer.parseInt(arr[8]));
        setACC(Integer.parseInt(arr[9]));
    }

    public Monster(ArrayList<Object> obj){

        for ( int i=0; i<obj.size(); i++){
            setData(i, obj.get(i));
        }
    }

    public int getBaseId(){return this.baseId;}
    public String getName(){return this.name;}
    public int getHP(){return this.HP;}
    public int getATK(){return this.ATK;}
    public int getDEF(){return this.DEF;}
    public int getSPD(){return this.SPD;}
    public int getCR(){return this.CRte;}
    public int getCD(){return this.CDmg;}
    public int getRES(){return this.RES;}
    public int getACC(){return this.ACC;}

    public void setBaseId(int id){this.baseId = id;}
    public void setName(String a){this.name = a;}
    public void setHP(int a){this.HP = a;}
    public void setATK(int a){this.ATK = a;}
    public void setDEF(int a){this.DEF = a;}
    public void setSPD(int a){this.SPD = a;}
    public void setCR(int a){this.CRte = a;}
    public void setCD(int a){this.CDmg = a;}
    public void setRES(int a){this.RES = a;}
    public void setACC(int a){this.ACC = a;}
    public void setSummonId(int a){this.summonId = a;}
    public int getSummonId(){return this.summonId;}




    public String toString(){
        StringBuilder build = new StringBuilder();
        build.append("SummonId: " + this.summonId + "\n");
        build.append("BaseId: " + this.baseId + " - ");
        build.append("\tMonster: " + this.name + "\n");
        build.append("\tHP: " + this.HP + "\n");
        build.append("\tATK: " + this.ATK + "\n");
        build.append("\tDEF: " + this.DEF + "\n");
        build.append("\tSPD: " + this.SPD + "\n");
        build.append("\tCrte: " + this.CRte + "\n");
        build.append("\tCdmg: " + this.CDmg + "\n");
        build.append("\tRES: " + this.RES + "\n");
        build.append("\tACC: " + this.ACC + "\n");
        return build.toString();
    }

    private void setData(int col, Object o){
        switch(col){
            case 0: this.baseId = (Integer)o;
                break;
            case 1: this.name = (String)o;
                break;
            case 2: this.HP = (Integer)o;
                break;
            case 3: this.ATK = (Integer)o;
                break;
            case 4: this.DEF = (Integer)o;
                break;
            case 5: this.SPD = (Integer)o;
                break;
            case 6: this.CRte = (Integer)o;
                break;
            case 7: this.CDmg = (Integer)o;
                break;
            case 8: this.RES = (Integer)o;
                break;
            case 9: this.ACC = (Integer)o;
                break;
            default: break;
        }

    }

    public String getAlias(){return this.alias;}


    public static void main(String[] args) {
        MonsterDB db = new MonsterDB();
        //Monster mon = new Monster(db.getRowDataObject(4));
//        db.getAllObjectData();
        //System.out.println(mon);
        //db.getRowDataObject("Monster", "Bellenus");
        db.closeConnection();
    }

    public boolean isEqual(Monster o) {
        if(this.baseId != o.baseId || !this.name.equals(o.name) || this.HP != o.HP || this.ATK != o.ATK || this.DEF != o.DEF || this.SPD != o.SPD || this.CRte != o.CRte || this.CDmg != o.CDmg || this.RES != o.RES || this.ACC != o.ACC || this.summonId != o.summonId)
            return false;
        return true;
    }

    public Monster clone(){
        return new Monster(this.baseId, this.name, this.HP, this.ATK, this.DEF, this.SPD, this.CRte, this.CDmg, this.RES, this.ACC);
    }
}
