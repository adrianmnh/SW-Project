package classes.subclasses;

import classes.Monster;

public class MonsterImageIcon extends MyImageIcon{

    public Monster monster;

    public MonsterImageIcon(String path){
        super(path);
    }
    public MonsterImageIcon(String path, Monster monster){
        super(path);
        this.monster = monster;
    }
}
