package database;

import classes.Monster;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MonsterDB extends Database{
//    private Connection con;
//    private Statement query;
//    private ResultSet result;

    public MonsterDB(){
        super();
        setTable("GameTool.Monster");
    }

    public ArrayList<Object> selectUserSummon(int userId){
        setTable("GameTool.Summon");
        ArrayList<Object> data = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        String query = String.format("SELECT * FROM %s WHERE AccountId = %d;", getTable(), userId);
        map.put("query", query);
        map.put("type", "all");
        data = this.execSelect(map);
        return data;
    }

    public ArrayList<Object> selectAllSimilarSummon(int userId, int baseId){
        setTable("GameTool.Summon");
        ArrayList<Object> data = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        String query = String.format("SELECT Name FROM %s WHERE AccountId = %d AND MonsterId = %d;", getTable(), userId, baseId);
        map.put("query", query);
        map.put("type", "all");
        data = this.execSelect(map);
        return data;
    }

    public ArrayList<Object> removeUserSummon(int userId, int summonId, String summonName){
        setTable("GameTool.Summon");
        System.out.println("Removing Summon: " + summonId + " " + summonName + " from user: " + userId);
        ArrayList<Object> deletedRows = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        String query = String.format("DELETE FROM %s WHERE AccountId = %d AND SummonId = %d AND Name = '%s';", getTable(), userId, summonId, summonName);
        map.put("query", query);
        map.put("type", "delete");

        deletedRows = this.execUpdate(map);
        return deletedRows;
    }

    public ArrayList<Object> addUserSummon(int userId, Monster monster, String summonName){
        setTable("GameTool.Summon");
        System.out.println("Adding Summon: " + monster.getName() + " with alias " + summonName + " toUser user: " + userId);
        ArrayList<Object> keyAdded = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        String query = String.format("INSERT INTO %s (AccountId, MonsterId, Name) VALUES(%d, %d, '%s');", getTable(), userId, monster.getBaseId(), summonName);
        map.put("query", query);
        map.put("type", "keys");
        keyAdded = this.execUpdate(map);
        return keyAdded;
    }

    public ArrayList<String> getBaseMonsters(){

        ArrayList<String> data = new ArrayList<>();
        try{

            String sqlQuery = "";
            sqlQuery = String.format("SELECT * from %s ", getTable());

            ResultSet result = getStatement().executeQuery(sqlQuery);

            while(result.next()) {
                String r = "";
                for (int col = 1; col <= result.getMetaData().getColumnCount(); col++)
                    r += result.getObject(col) + " ";
                data.add(r);
            }




        }catch(SQLException ex){
            System.out.println("Error found: " + ex);
        } finally {
            this.closeConnection();
            return data;
        }
    }

    /*
        *** Adding individual monsters to userid
     */
    public void createUserMonsterDatabase(){
        try{
            String sqlQuery = "CREATE TABLE sql5411932.user_monsters ( userid INT(4) NOT NULL , monsterid INT(2) NOT NULL, name VARCHAR(30) NOT NULL," +
                    "HP INT(5) NOT NULL, ATK INT(5) NOT NULL, DEF INT(5) NOT NULL, SPD INT(5) NOT NULL, "+
                    "CR INT(5) NOT NULL, CD INT(5) NOT NULL, RES INT(5) NOT NULL, ACC INT(5) NOT NULL, engraved BOOLEAN NOT NULL) ENGINE = InnoDB;";

            getStatement().execute(sqlQuery);

        }catch (SQLException e){
            System.out.println("Error found: " + e);
        }
    }

    public static void main(String[] args) {
//        MonsterDB connct = new MonsterDB();
//        connct.addToMonsterDatabase();
//        //connct.removeMonsterFromDatabase();
//        //connct.getAllObjectData();
//        //connct.printAllTableData();
//        //connct.changeColumnNames();
////        connct.createUserMonsterDatabase();
//        connct.closeConnection();

    }


}