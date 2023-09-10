package database;
import runes.Rune;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RuneDB extends Database {
//    public ArrayList<Rune> runes;


    public RuneDB(){
        super();
        setTable("GameTool.Rune");
    }

    public boolean addRuneToUser(int user, Rune r){
        boolean success = false;
        try{
            String sqlQuery = addRuneSQLQuery(user, r);
            System.out.println(getStatement().execute(sqlQuery)  );
            System.out.printf("%s\n****rune added to user %d****", r, user);
            success = true;
            System.out.println("success: " + success);
        }catch (SQLException e){
            System.out.println("Error found: " + e);
            System.out.println("success: " + success);
            success = false;
        }
        return success;
    }
    private String addRuneSQLQuery(int user, Rune r){
        //checkIfUserHasRune(user, r);
        System.out.println("----------------------------------");
        int grade = Integer.parseInt(r.getGradeString());
        int innate = r.getInnateString().equals("yes") ? 1 : 0;
        int position = Integer.parseInt(r.getPositionString());
        String set = r.getSetString(), mainStat = r.getMainStat().getMainStatAttribute();
        StringBuilder s = new StringBuilder();
        s.append( String.format("INSERT INTO " + this.getTable() + " VALUES (%d, ", user) );
        s.append( String.format("%d, '%s', %d, %d, '%s', ", grade, set, position, innate, mainStat) );

        s.append( String.format("'%s', %s, ", r.getSubStats().get(0).getSubStat(), r.getSubStats().get(0).getSubValue()) );
        s.append( String.format("'%s', %s, ", r.getSubStats().get(1).getSubStat(), r.getSubStats().get(1).getSubValue()) );
        s.append( String.format("'%s', %s, ", r.getSubStats().get(2).getSubStat(), r.getSubStats().get(2).getSubValue()) );
        s.append( String.format("'%s', %s", r.getSubStats().get(3).getSubStat(), r.getSubStats().get(3).getSubValue()) );

        if(r.getRuneInnate()){
            s.append( String.format(", '%s', %s ", r.getSubStats().get(4).getSubStat(), r.getSubStats().get(4).getSubValue()) );
        } else {
            s.append(", null, null");
        }
        s.append(");");

        System.out.println(s.toString());
//        System.out.println("INSERT INTO " + this.getTable() + " VALUES (1, 6, 'Violent', 1, 1, 'ATK', 'HP%', 23, 'ACC', 16, 'SPD', 5, 'CRte', 10, 'CDmg', 6);");

        return s.toString();
    }
//
    public boolean userHasRune(int user, Rune rune){
        boolean toReturn = false;
        try{
            ArrayList<Rune> user_runes = getUserRunes(10);
            for ( Rune r : user_runes ) {
                if( rune.compareTo(r) == 0 ){
                    System.out.println("Comparing, true flag hit, user has rune");
                    return true;
                }
                System.out.print("-NOhit-  ");
            }

            if(toReturn == false){
//                getStatement().execute(addRuneSQLQuery(user, rune));
//                System.out.println(rune+ " rune added to" + user);
//                user_runes.add(rune);
                return false;
            }
        }catch(Exception e){
            System.out.println("Error found here. " + e.getLocalizedMessage());
        }
        return toReturn;
    }
//
    public ArrayList<Rune> getUserRunes(int userid){
        ArrayList<Rune> runes = null;
        try{
            String sqlQuery = "SELECT * FROM " + getTable() + " WHERE AccountId = " + userid;
            ResultSet result = getStatement().executeQuery(sqlQuery);
            runes = new ArrayList();
            while(result.next()){
                String r = "";
                for(int i=3; i<=result.getMetaData().getColumnCount(); i++){
                    if(result.getObject(i) != null )
                        r += result.getObject(i) + " ";
                }
                runes.add(new Rune(r));
                System.out.println("Rune from DB: " + r);
            }
            return runes;
        }catch(Exception e){
            System.out.println("Error found. " + e);
        }
        return null;
    }

    public static void main(String[] args) {
        RuneDB db = new RuneDB();
//        db.addColumns();
        //db.createRunePropertyDatabase();
        //db.addRuneToUser(10, null);
        db.closeConnection();
    }
}
