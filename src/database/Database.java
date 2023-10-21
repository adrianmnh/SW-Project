package database;
import runes.Rune;

import javax.xml.transform.Result;
import java.sql.*;

import java.util.ArrayList;

import java.sql.Connection;
import java.util.HashMap;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public ArrayList<Object> execSelect(HashMap<String, Object> map){
        ArrayList<Object> data = new ArrayList<Object>();
        try{
            String query = map.get("query").toString();
            String selectType = map.get("type").toString() == null ? "" : map.get("type").toString();

            switch(selectType){
                case "all":
                    System.out.println("Executing select query: " + query);
                    ResultSet result = satement.executeQuery(query);
                    // get column names:
                    ResultSetMetaData rsmd = result.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    ArrayList<Object> columnNames = new ArrayList<Object>();
                    for(int i=1; i<=columnsNumber; i++)
                        columnNames.add(rsmd.getColumnName(i));
                    data.add(columnNames);
                    while(result.next()){
                        ArrayList<Object> row = new ArrayList<Object>();
                        for(int col=1; col<=result.getMetaData().getColumnCount(); col++)
                            row.add(result.getObject(col));
                        data.add(row);
                    }
                    System.out.println("Retrieved rows: " + data.size());
                    break;

                case "rows":
                    System.out.println("Executing select query: " + query);
                    ResultSet result2 = satement.executeQuery(query);
                    result2.next();
                    ArrayList<Object> row = new ArrayList<Object>();
                    for(int col=1; col<=result2.getMetaData().getColumnCount(); col++)
                        row.add(result2.getObject(col));
                    data.add(row);
                    System.out.println("Retrieved row: " + data);
                    break;

                case "count":
                    System.out.println("Executing select query: " + query);
                    ResultSet result3 = satement.executeQuery(query);
                    result3.next();
                    data.add(result3.getInt(1));
                    System.out.println("Retrieved count: " + data);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("\n\n::::::::::::::Error found :" + ex.getLocalizedMessage() + ":::::::::::::\n\n");
            if(ex.getLocalizedMessage().contains("permission")){
                data.add(-99);
            } else {
                data.add(-1);
            }
            SQLException sqlException = (SQLException)ex;
            System.out.println("Error Code:" + sqlException.getErrorCode());
        } finally {
            this.closeConnection();
            return data;
        }

    }

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
            System.out.println("\n\n::::::::::::::Error found :" + ex.getLocalizedMessage() + ":::::::::::::\n\n");
            if(ex.getLocalizedMessage().contains("permission")){
                data.add(-99);
            } else {
                data.add(-1);
            }
            SQLException sqlException = (SQLException)ex;
            System.out.println("Error Code:" + sqlException.getErrorCode());
        } finally {
            this.closeConnection();
            return data;
        }

    }

//    public ArrayList<Object>

    public void initConnection(){
        try{

            String connectionString = "" +
                    "jdbc:sqlserver://localhost:1433;" +
                    "database=SummonersWar;user=guestlogin;" +
                    "password=guestlogin$%#@33096$%#@;" +
                    "encrypt=true;" +
                    "trustServerCertificate=true;";
//            System.out.println(connectionString);

            connection = DriverManager.getConnection(connectionString);

//            System.out.println("------- Connection established ------- :: Database:: " + connection.getCatalog() + " ClientInfo: " + connection.getClientInfo());
            System.out.println("------- Connection established -------");

            satement = connection.createStatement();


        }catch(Exception ex){
            System.out.println("Error is found, connection not started :"+ex);
        }
    }
    
    public void closeConnection() {
        try{
            if(!connection.isClosed()){
                connection.close();
                System.out.println("------- Connection Closed -----------");
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
