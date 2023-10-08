package classes;

public class Summon extends Monster{

    private int summonId;
    private String summonName;
    private int[] runesEngraved;


    public Summon(Monster m, int summonId, String summonName){
        setBaseId(m.getBaseId());
        setName(m.getName());
        setHP(m.getHP());
        setATK(m.getATK());
        setDEF(m.getDEF());
        setSPD(m.getSPD());
        setCR(m.getCR());
        setCD(m.getCD());
        setRES(m.getRES());
        setACC(m.getACC());
        setSummonId(summonId);
        setSummonName(summonName);
        runesEngraved = new int[]{summonId, -1, -1, -1, -1, -1, -1};

    }
    public Summon(int baseId, String name, int HP, int ATK, int DEF, int SPD, int CRte, int CDmg, int RES, int ACC, int summonId, String summonName){
        super(baseId, name, HP, ATK, DEF, SPD, CRte, CDmg, RES, ACC);
        setSummonId(summonId);
        setSummonName(summonName);
        runesEngraved = new int[]{summonId, -1, -1, -1, -1, -1, -1};
    }

    // setters and getters
    public void setSummonId(int summonId) {
        this.summonId = summonId;
    }
    public int getSummonId() {
        return summonId;
    }
    public void setSummonName(String summonName) {
        this.summonName = summonName;
    }
    public String getSummonName() {
        return summonName;
    }
    public void removeAllRunes(){
        for (int i = 1; i <= 7; i++) {
            runesEngraved[i] = -1;
        }
    }
}
