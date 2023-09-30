package classes.subclasses;

import classes.Monster;

public class BaseMonsterList extends IconArrayList<MonsterImageIcon>{

    public int baseId;

//    public int[] baseID_toIndex = new int[1000];

    public BaseMonsterList(){
        super();
    }

    public void addMonster(MonsterImageIcon monsterImageIcon){
        super.add(monsterImageIcon);
//        baseID_toIndex[monsterImageIcon.baseId] = super.size() - 1;
    }

    public void addMonster(MonsterImageIcon monsterImageIcon, Monster monster){
        monsterImageIcon.setMonster(monster);
        monsterImageIcon.setBaseId(monster.getBaseId());
        super.add(monsterImageIcon);
    }


    public MonsterImageIcon getBaseMonster(int baseId) {

        for (MonsterImageIcon monsterImageIcon : this) {
            if (monsterImageIcon.baseId == baseId) {
                return monsterImageIcon;
            }
        }
        return null;
    }

    public MonsterImageIcon getBaseMonster(String name) {
        for (MonsterImageIcon monsterImageIcon : this) {
            if (monsterImageIcon.monster.getName().equals(name)) {
                return monsterImageIcon;
            }
        }
        return null;
    }


}
