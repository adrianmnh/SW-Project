package database;
import classes.Monster;
import classes.subclasses.RuneBag;
import runes.Rune;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RuneDB extends Database {
//    public ArrayList<Rune> runes;


    public RuneDB(){
        super();
        setTable("GameTool.Rune");
    }

    public int execAddRune(int user, Rune r){
        boolean success = false;
        int generatedId = -1;
        try{
            String sqlQuery = insertRuneQuery(user, r);
//            System.out.println( );
            getStatement().executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            System.out.printf("%s\n****rune added to user %d****\n", r, user);
            success = true;
//            System.out.println("success: " + success
//                    + "\nprimary key: " + primaryKey
//                    + "\nsqlQuery: " + sqlQuery);

            ResultSet generatedKeys = getStatement().getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1); // Assuming the generated key is an integer
                System.out.println("Inserted row with generated ID: " + generatedId);
            }
        }catch (SQLException e){
            System.out.println("Error found: " + e);
            System.out.println("success: " + success);
            success = false;
        }
        this.closeConnection();
        return generatedId;
    }
    private String insertRuneQuery(int user, Rune r){
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
        s.append(", 0"); // is equipped
        s.append(");");

//        System.out.println(s.toString());
//        System.out.println("INSERT INTO " + this.getTable() + " VALUES (1, 6, 'Violent', 1, 1, 'ATK', 'HP%', 23, 'ACC', 16, 'SPD', 5, 'CRte', 10, 'CDmg', 6);");

        return s.toString();
    }
    public int execEngraveRuneQuery(int user, Monster monster, Rune rune){
        int rowsUpdated = -1;
        String sql = "";
        try{
            sql = String.format("UPDATE %s SET Rune%d = %d WHERE AccountId = %d AND MonsterId = %d;",
                    "GameTool.Engraved", rune.getPosInt(), rune.getId(), user, monster.getId());
            System.out.println(sql);
            rowsUpdated = getStatement().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("Rows updated: " + rowsUpdated);

        } catch (SQLException e) {
            System.out.println("Error found: " + e);
        }
        this.closeConnection();
        return rowsUpdated;
    }
//
    public RuneBag getUserRunes(int userid){
        RuneBag runes = new RuneBag();
        try{
            String sqlQuery = "SELECT * FROM " + getTable() + " WHERE AccountId = " + userid;
            ResultSet result = getStatement().executeQuery(sqlQuery);
            while(result.next()){
                String r = "";
                for(int i=1; i<=result.getMetaData().getColumnCount(); i++){
                    if(i!=2)
                        r += result.getObject(i) + " ";
                }
//                System.out.println(r);
                runes.add(new Rune(r));
//                System.out.println("Rune from DB: " + r);
            }
            this.closeConnection();
        }catch(Exception e){
            System.out.println("Error found. " + e);
        }
        return runes;
    }

    public static void main(String[] args) {
        RuneDB db = new RuneDB();
//        db.addColumns();
        //db.createRunePropertyDatabase();
        //db.addRuneToUser(10, null);
        db.closeConnection();
    }
}
