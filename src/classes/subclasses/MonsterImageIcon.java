package classes.subclasses;

import classes.Monster;

public class MonsterImageIcon extends MyImageIcon {

    public Monster monster;
    public int baseId = -1;

    public MonsterImageIcon(String path){
        super(path);
    }

    public MonsterImageIcon(String path, Monster monster){
        super(path);
        this.monster = monster;
    }
    public MonsterImageIcon(String path, Monster monster, int baseId){
        super(path);
        this.monster = monster;
        this.baseId = baseId;
    }

    public void setMonster(Monster monster){
        this.monster = monster;
    }

    public void setBaseId(int baseId){
        this.baseId = baseId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MonsterImageIcon{");
        sb.append("imgName='").append(imgName).append('\'');
        sb.append("\nmonster: \n").append(monster);
        sb.append(", baseId=").append(baseId);
        sb.append('}');
        return sb.toString();
    }

    public boolean isEqual(MonsterImageIcon o) {
        if(this.baseId != o.baseId || !this.monster.isEqual(o.monster) || !this.imgName.equals(o.imgName) )
            return false;
        return true;
    }

    public MonsterImageIcon clone(){
        return new MonsterImageIcon(this.path, this.monster, this.baseId);
    }
}
