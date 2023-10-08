package classes.subclasses;

import classes.Monster;

public class MonsterImageIcon extends MyImageIcon implements Comparable<MonsterImageIcon> {

    public Monster monster;
    public int baseId = -1;
    public int summonId = -1;

    public String alias;

    public MonsterImageIcon(String path){
        super(path);
    }

    public MonsterImageIcon(){
        super();
        this.baseId = -99;
        this.summonId = -99;
        this.monster = new Monster();
        this.alias = "DUMMY";
        this.imgName = "DUMMY";
        this.imgResource = null;
        this.setImage(null);
    }

    public MonsterImageIcon(String path, Monster monster){
        super(path);
        this.monster = monster;
    }
    public MonsterImageIcon(String path, Monster monster, int baseId){
        super(path);
        this.monster = monster;
        this.baseId = baseId;
        this.summonId = monster.getSummonId();
        this.alias = monster.getName();
    }

    public void setMonster(Monster monster){
        this.monster = monster;
    }

    public void setBaseId(int baseId){
        this.baseId = baseId;
    }

    public void setSummonId(int summonId){
        this.summonId = summonId;
        this.monster.setSummonId(summonId);
    }

    public void setAlias(String alias){
        this.alias = alias;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MonsterImageIcon{\n");
        sb.append("\timgName='").append(imgName).append("\',");
        sb.append("\talias='").append(alias).append("\',\n");
        sb.append("\timgPath='").append(imgPath).append("\',\n");
        sb.append("\tbaseId='").append(baseId).append("\',\n");
        sb.append("\tsummonId='").append(summonId).append("\',\n");
//        sb.append("Monster:").append(monster);
        sb.append('}');
        return sb.toString();
    }

    public boolean isEqual(MonsterImageIcon o) {
        if(this.baseId != o.baseId || !this.monster.isEqual(o.monster) || !this.imgName.equals(o.imgName) || !this.imgPath.equals(o.imgPath) || this.summonId != o.summonId || !this.alias.equals(o.alias) )
            return false;
        return true;
    }

    public MonsterImageIcon clone(){
        return new MonsterImageIcon(this.imgPath, this.monster.clone(), this.baseId);
    }

    @Override
    public int compareTo(MonsterImageIcon o) {
        return this.isEqual(o) ? 0 : 1;
    }
}
