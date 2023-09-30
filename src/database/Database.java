package database;
import runes.Rune;

import javax.xml.transform.Result;
import java.sql.*;

import java.util.ArrayList;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

public abstract class Database {

    private Connection connection;
    private Statement satement;
    private String dbtable;

    /*
     * Dont forget to call Close Connection before exiting program
     */

    public Database(){
        initConnection();
    }

    //statement execute(sql) returns SQL Warning or "printed message"
    //int = statement executeUpdate(sql, RETURN_GE..) -> rowsUpdated
    // statement executeUpdate(sql, RETURN_GENERATED_KEYS),  ResultSet generated = statement.getGeneratedKeys().next(), generated.getInt(1)

    public ArrayList<Object> execUpdate(HashMap<String, Object> map) {
        ArrayList<Object> data = new ArrayList<Object>();
        try {
            String updateType = map.get("type").toString() == null ? "" : map.get("type").toString();
            String query = map.get("query").toString();

            switch(updateType) {
                case "rows":
                    System.out.println("Executing update query: " + query);
                    int rowsUpdated = satement.executeUpdate(query);
                    data.add(rowsUpdated);
                    System.out.println("Rows updated: " + data);
                    break;

                case "keys":
                    System.out.println("Executing update query: " + query);
                    getStatement().executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                    ResultSet generatedKeys = getStatement().getGeneratedKeys();
                    while(generatedKeys.next()){
                        data.add(generatedKeys.getInt(1));
                    }
                    System.out.println("Generated keys: " + data);
                    break;

                case "delete":
                    System.out.println("Executing update query: " + query);
                    int rowsDeleted = getStatement().executeUpdate(query);
                    data.add(rowsDeleted);
                    System.out.println("Deleted keys: " + data);
                    break;
            }

        } catch (Exception ex) {
            System.out.println("Error found :" + ex.getLocalizedMessage());
        } finally {
            this.closeConnection();
            return data;
        }

    }

    public void initConnection(){
        try{

            String connectionString = "" +
                    "jdbc:sqlserver://localhost:1433;" +
                    "database=SummonersWar;user=admin;" +
//                    "password=admin$%#@29174$%#@;" +
                    "password=admin$%#@33096$%#@;" +
                    "encrypt=true;" +
                    "trustServerCertificate=true;";
//            System.out.println(connectionString);

//            System.out.println("Database.Connection");

            connection = DriverManager.getConnection(connectionString);

//            connection = DriverManager

//            System.out.println("\n -- Connection established -- \n\n --" + connection);
            System.out.println(connection.getClientInfo());
            System.out.println("\n -- Connection established -- \n\n --");

            satement = connection.createStatement();


        }catch(Exception ex){
            System.out.println("Error is found, connection not started :"+ex);
        }
    }
    
    public void closeConnection() {
        try{
            if(!connection.isClosed()){
                connection.close();
                System.out.println("Connection closed");
            }
        }catch(SQLException e) {
            System.out.println("No active connection to close");
        }
    }
    public void setTable(String s){this.dbtable = s;}
    public String getTable(){
        return this.dbtable;
    }
    public Statement getStatement(){return this.satement; }

    public ArrayList<Object> getAllObjectData(){
        try{
            System.out.println("Retrieving all data from \"" + dbtable + "\":");
            String sql = "select * from " + dbtable;
            ResultSet result = satement.executeQuery(sql);
            System.out.println("Number of columns: " + result.getMetaData().getColumnCount());

            ArrayList<Object> monsterList = new ArrayList<Object>();
            while(result.next()){
                ArrayList<Object> monster = new ArrayList<Object>();
                for(int col=1; col<result.getMetaData().getColumnCount(); col++)
                    monster.add( result.getObject(col) );
                monsterList.add(monster);
            }
            for(Object o : monsterList)
                System.out.println(o);
            return monsterList;

        }catch(Exception ex){
            System.out.println("Error found :"+ex);
        }

        return null;

    }

    public ArrayList<Object> getRowDataObject(int i){


        try{
            System.out.println("Retrieving row " + i + " from " + getTable() + ":");
            ArrayList<Object> rowObject = new ArrayList<Object>();

            String sqlQuery = "";
            sqlQuery = String.format("SELECT * from %s WHERE monster_id = %d", getTable(), i);

            ResultSet result = getStatement().executeQuery(sqlQuery);

            result.next();

            for(int col = 1; col < result.getMetaData().getColumnCount(); col++){
                rowObject.add(result.getObject(col));
            }
            //System.out.println(rowObject);
            return rowObject;

        }catch(SQLException ex){
            System.out.println("Error found: " + ex);
        }
        return null;
    }

    public ArrayList<Object> getRowDataObject(String column_name, String value){


        try{
            System.out.printf("Retrieving row where %s is %s from %s\n", column_name, value, getTable());
            ArrayList<Object> rowObject = new ArrayList<Object>();

            String sqlQuery = "";
            sqlQuery = String.format("SELECT * from %s WHERE %s = '%s'", getTable(), column_name, value);

            ResultSet result = getStatement().executeQuery(sqlQuery);
            System.out.println("Retrieved rows: " + result.getFetchSize());
            result.next();

            for(int col = 1; col < result.getMetaData().getColumnCount(); col++){
                rowObject.add(result.getObject(col));
            }
            System.out.println(rowObject);
            return rowObject;

        }catch(SQLException ex){
            System.out.println("Error found: " + ex);
        }
        return null;
    }

    public ArrayList<Object> getAllObjects(){


        try{
            ArrayList<Object> data = new ArrayList<Object>();

            String sqlQuery = "";
            sqlQuery = String.format("SELECT * from %s ", getTable());

            ResultSet result = getStatement().executeQuery(sqlQuery);
            System.out.println("Retrieved rows: " + result.getFetchSize());
            while(result.next()) {
                String r = "";
                for (int col = 2; col < result.getMetaData().getColumnCount(); col++)
                    r += result.getObject(col) + " ";
                data.add(r);
            }
            return data;



        }catch(SQLException ex){
            System.out.println("Error found: " + ex);
        }
        return null;
    }

    public void execQuery(String q){

        try{
            getStatement().execute(q);
            System.out.println("Query executed");
        }catch(Exception e){
            System.out.println("Error found. " + e);
        }

    }

    //RuneDatabaseMethods
//    public boolean addRuneToUser(int user, Rune r){
//        try{
//            if(!userHasRune(user, r)){
//                String sqlQuery = addRuneSQLQuery(user, r);
//                getStatement().execute(sqlQuery);
//                System.out.printf("%s   ****rune added to user %d****\n", r, user);
//                return true;
//            }
//        }catch (SQLException e){
//            System.out.println("Error found on addRuneToUser: " + e);
//            return false;
//        }
//        return false;
//    }
    private String addRuneSQLQuery(int user, Rune r){
        //checkIfUserHasRune(user, r);
        //	userid	runeid	runegrade-runeset-position-innate-mainstat	sub0	val0	sub1	val1	sub2	val2	sub3	val3	sub4	val4
        String grade = r.getGradeString(), set = r.getSetString(), pos = r.getPositionString(), stat = r.getMainStat().getMainStatAttribute(), innate = r.getInnateString();
        StringBuilder s = new StringBuilder();
        s.append( String.format("INSERT INTO user_runes VALUES ('%s', NULL, ", user) );
        s.append( String.format("'%s', '%s', '%s', '%s', '%s', ", grade, set, pos, innate, stat) );
        if(r.getRuneInnate()){
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(0).getSubStat(), r.getSubStats().get(0).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(1).getSubStat(), r.getSubStats().get(1).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(2).getSubStat(), r.getSubStats().get(2).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(3).getSubStat(), r.getSubStats().get(3).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(4).getSubStat(), r.getSubStats().get(4).getSubValue()) );
        }
        else{
            s.append("NULL, NULL, ");
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(0).getSubStat(), r.getSubStats().get(0).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(1).getSubStat(), r.getSubStats().get(1).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(2).getSubStat(), r.getSubStats().get(2).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(3).getSubStat(), r.getSubStats().get(3).getSubValue()) );
        }
        s.append("false );");
        return s.toString();
    }
//    public boolean userHasRune(int user, Rune rune){
//        boolean toReturn = false;
//        try{
//            ArrayList<Rune> user_runes = getUserRunes(user);
//            for ( Rune r : user_runes ) {
//                if( rune.compareTo(r) == 0 ){
//                    System.out.println("Comparing, true flag hit, user has rune");
//                    return true;
//                }
//                System.out.print("-NOhit-  ");
//            }
//
//            if(toReturn == false){
////                getStatement().execute(addRuneSQLQuery(user, rune));
////                System.out.println(rune+ " rune added to" + user);
////                user_runes.add(rune);
//                return false;
//            }
//        }catch(Exception e){
//            System.out.println("Error found at userHasRune\n " + e.getLocalizedMessage() + "\n"+e.getStackTrace()+"\n"+e.getClass());
//        }
//        return toReturn;
//    }
//    public ArrayList<Rune> getUserRunes(int userid){
//        ArrayList<Rune> runes;
//        // return and arrayList of Runes in the correct format, no null
//        try{
////            String sqlQuery = "select runegrade, runeset, position, innate, mainstat, sub1, val1, sub2, val2, sub3, val3, sub4, val4, sub0, val0 from user_runes where userid = "+userid;
//            String sqlQuery =
//            System.out.println(sqlQuery);
//            ResultSet result = getStatement().executeQuery(sqlQuery);
//            //System.out.println("\nThis is existing user TABLE user_runes data fixed without NULL");
//            runes = new ArrayList<>();
//            while(result.next()){
//                String r = "";
//                for(int i=1; i<=result.getMetaData().getColumnCount(); i++){
//                    if(result.getObject(i) != null )
//                        r += result.getObject(i) + " ";
//                }
//                //System.out.println(r + " String used to create Rune objects from existing data");
//                runes.add(new Rune(r));
//            }
////            System.out.println("\nList of existing Runes:");
////            for(Rune r : runes)System.out.println(r);
//            return runes;
//        }catch(Exception e){
//            System.out.println("Error found. " + e);
//        }
//        return null;
//    }

    public void printResultSet(ResultSet resultSet) {
        try {
            System.out.println("\nPrinting data from ResultSet:");
            while (resultSet.next()) {
                ArrayList<Object> row = new ArrayList<Object>();
                for (int col = 1; col <= resultSet.getMetaData().getColumnCount(); col++)
                    row.add(resultSet.getObject(col));
                System.out.print(row + "\n");
            }
            System.out.println();
        } catch (Exception ex) {
            System.out.println("Error found :" + ex);
        }
    }

    public void printAllTableData() {
        try {
            System.out.println("\nPrinting data from online Database table \"" + dbtable + "\":");
            String sql = "select * from " + dbtable;
            ResultSet resultSet = satement.executeQuery(sql);

            while (resultSet.next()) {
                ArrayList<Object> row = new ArrayList<Object>();
                for (int col = 1; col <= resultSet.getMetaData().getColumnCount(); col++)
                    row.add(resultSet.getObject(col));
                System.out.print(row + " ");
            }
            System.out.println();
        } catch (Exception ex) {
            System.out.println("Error found :" + ex);
        }
    }

    public static void main(String[] args) {
        Database db;
        String query;
        HashMap<String, Object> map = new HashMap<String, Object>();

        db = new MonsterDB();
        query = "    INSERT INTO GameTool.Account VALUES " +
                "('nameGoesHere', 'password', 'email@email.com'), " +
                "('nameGoesHere2', 'password', 'email2@email.com');"  ;
        map.put("query", query);
        map.put("type", "rows");
        db.execUpdate(map);

        db = new MonsterDB();
//        query = "    DELETE FROM GameTool.Account WHERE AccountUsername LIKE 'nameGoes%';"   ;
        query = "    DELETE FROM GameTool.Account WHERE AccountUsername LIKE 'nameGoesHere';"   ;
        map.put("query", query);
        map.put("type", "delete");
        db.execUpdate(map);
    }


}
