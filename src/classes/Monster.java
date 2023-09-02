package classes;

import java.util.ArrayList;

public class Monster {

    private int id;
    private String name;
    private int HP;
    private int ATK;
    private int DEF;
    private int SPD;
    private int CRte;
    private int CDmg;
    private int RES;
    private int ACC;

    public Monster(){
        this.id = 0;
        this.name = null;
        this.HP = 0;
        this.ATK = 0;
        this.DEF = 0;
        this.SPD = 0;
        this.CRte = 0;
        this.CDmg = 0;
        this.RES = 0;
        this.ACC = 0;
    }
    public Monster(int id, String name, int HP, int ATK, int DEF, int SPD, int CRte, int CDmg, int RES, int ACC){
        this.id = id;
        this.name = name;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.SPD = SPD;
        this.CRte = CRte;
        this.CDmg = CDmg;
        this.RES = RES;
        this.ACC = ACC;
    }
    public Monster(String longstring){
        String[] arr;
        arr = longstring.split(" ");
        setID(Integer.parseInt(arr[0]));
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

    public int getID(){return this.id;}
    public String getName(){return this.name;}
    public int getHP(){return this.HP;}
    public int getATK(){return this.ATK;}
    public int getDEF(){return this.DEF;}
    public int getSPD(){return this.SPD;}
    public int getCR(){return this.CRte;}
    public int getCD(){return this.CDmg;}
    public int getRES(){return this.RES;}
    public int getACC(){return this.ACC;}

    public void setID(int a){this.id = a;}
    public void setName(String a){this.name = a;}
    public void setHP(int a){this.HP = a;}
    public void setATK(int a){this.ATK = a;}
    public void setDEF(int a){this.DEF = a;}
    public void setSPD(int a){this.SPD = a;}
    public void setCR(int a){this.CRte = a;}
    public void setCD(int a){this.CDmg = a;}
    public void setRES(int a){this.RES = a;}
    public void setACC(int a){this.ACC = a;}




    public String toString(){
        StringBuilder build = new StringBuilder();
        build.append("Monster name: " + this.name + "\n");
        build.append("HP: " + this.HP + "\n" + "ATK: " + this.ATK + "\n");
        build.append("DEF: " + this.DEF + "\n" + "SPD: " + this.SPD + "\n");
        build.append("Crte: " + this.CRte + "\n" + "Cdmg: " + this.CDmg + "\n");
        build.append("RES: " + this.RES + "\n" + "ACC: " + this.ACC + "\n");
        return build.toString();
    }

    private String getImageIcon(String name){

        String toReturn = null;

        switch(name){
            case "Artamiel": toReturn = "monsters/Artamiel.jpg";
            break;
            case "Barbara": toReturn = "monsters/Barbara.jpg";
            break;
            case "Bastet": toReturn = "monsters/bastet.jpg";
            break;
            case "Bellenus": toReturn = "monsters/bellenus.jpg";
            break;
            case "Chiwu": toReturn = "monsters/chiwu.jpg";
            break;
            case "Ethna": toReturn = "monsters/ethna.jpg";
            break;
            case "Laika": toReturn = "monsters/laika.jpg";
            break;
            case "Monkey": toReturn = "monsters/monkey.jpg";
            break;
            case "Oberon": toReturn = "monsters/oberon.jpg";
            break;
            case "Perna": toReturn = "monsters/perna.jpg";
            break;
            case "Ryu": toReturn = "monsters/ryu.jpg";
            break;
            case "Seara": toReturn = "monsters/seara.jpg";
            break;
            case "Susano": toReturn = "monsters/susano.jpg";
            break;
            case "Sylvia": toReturn = "monsters/sylvia.jpg";
            break;
            case "Theomars": toReturn = "monsters/theo.jpg";
            break;
            case "Tiana": toReturn = "monsters/tiana.jpg";
            break;
            default: break;
        }

        return toReturn;

    }

    private void setData(int col, Object o){
        switch(col){
            case 0: this.id = (Integer)o;
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


    public static void main(String[] args) {
        MonsterDB db = new MonsterDB();
        //Monster mon = new Monster(db.getRowDataObject(4));
//        db.getAllObjectData();
        //System.out.println(mon);
        //db.getRowDataObject("Monster", "Bellenus");
        db.closeConnection();
    }
}
