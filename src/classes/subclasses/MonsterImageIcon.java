package classes.subclasses;

import classes.Monster;

public class MonsterImageIcon extends MyImageIcon{

    public Monster monster;
    public int index;

    public MonsterImageIcon(String path){
        super(path);
    }
    public MonsterImageIcon(String path, Monster monster){
        super(path);
        this.monster = monster;
    }
    public MonsterImageIcon(String path, Monster monster, int index){
        super(path);
        this.monster = monster;
        this.index = index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MonsterImageIcon{");
        sb.append("name='").append(name).append('\'');
        sb.append(", monster=").append(monster);
        sb.append(", index=").append(index);
        sb.append('}');
        return sb.toString();
    }
}
