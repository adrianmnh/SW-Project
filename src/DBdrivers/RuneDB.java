package DBdrivers;
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
    public void createRunePropertyDatabase(){
        try{
            String sqlQuery = "";

            //rune sets
            //String sqlQuery = "CREATE TABLE sql5410429.rune_sets ( runeset VARCHAR(15) NOT NULL ) ENGINE = InnoDB;";
            //sqlQuery = "insert into rune_sets (runeset) values ('Violent'), ('Swift'), ('Rage'), ('Will'), ('Blade'), ('Nemesis')";
            //sqlQuery = "ALTER TABLE `rune_sets` ADD PRIMARY KEY (runeset);";
            //sqlQuery = "ALTER TABLE `user_runes` ADD CONSTRAINT `runeset_constraint` FOREIGN KEY (`runeset`) REFERENCES `rune_sets`(`runeset`) ON DELETE RESTRICT ON UPDATE RESTRICT;";


            //rune position
            //sqlQuery = "CREATE TABLE sql5410429.rune_pos ( runepos INT(1) NOT NULL ) ENGINE = InnoDB;
            //sqlQuery = "insert into rune_pos (runepos) values (1), (2), (3), (4), (5), (6)";
            //sqlQuery = "alter table rune_pos add primary key (runepos)";
            //sqlQuery = "alter table user_runes add CONSTRAINT runepos_constraint FOREIGN KEY (runepos) REFERENCES rune_pos(runepos) ON DELETE RESTRICT ON UPDATE RESTRICT;

            //rune innate
            //sqlQuery = "CREATE TABLE sql5410429.rune_innate ( innate VARCHAR(3) NOT NULL ) ENGINE = InnoDB;
            //sqlQuery = "insert into rune_innate (innate) values ('no'), ('yes');
            //sqlQuery = "alter table rune_innate add primary key (innate)";
            //sqlQuery = "alter table user_runes add CONSTRAINT innate_constraint FOREIGN KEY (innate) REFERENCES rune_innate(innate) ON DELETE CASCADE ON UPDATE CASCADE;



            //rune stats
            //String sqlQuery = "CREATE TABLE sql5410429.rune_stats ( runestat VARCHAR(5) NOT NULL ) ENGINE = InnoDB;";
            //sqlQuery = "insert into rune_stats (runestat) values ('HP%'), ('ATK%'), ('DEF%'), ('SPD'), ('Crte'), ('CDmg'), ('RES'), ('ACC'), ('hp'), ('atk'), ('def')";
            //sqlQuery = "alter table rune_stats add PRIMARY KEY (runestat)";
            //sqlQuery = "alter table user_runes add CONSTRAINT runestat_constraint FOREIGN KEY (mainstat) REFERENCES rune_stats(runestat) ON DELETE RESTRICT ON UPDATE RESTRICT;";
            //sqlQuery = "alter table user_runes add CONSTRAINT innate_constraint FOREIGN KEY (innate) REFERENCES rune_stats(runestat) ON DELETE RESTRICT ON UPDATE RESTRICT;";
            //sqlQuery = "alter table user_runes add CONSTRAINT sub1_constraint FOREIGN KEY (sub1) REFERENCES rune_stats(runestat) ON DELETE RESTRICT ON UPDATE RESTRICT; ";
            //sqlQuery = "alter table user_runes add CONSTRAINT sub2_constraint FOREIGN KEY (sub2) REFERENCES rune_stats(runestat) ON DELETE RESTRICT ON UPDATE RESTRICT";
            //sqlQuery = "alter table user_runes add CONSTRAINT sub3_constraint FOREIGN KEY (sub3) REFERENCES rune_stats(runestat) ON DELETE RESTRICT ON UPDATE RESTRICT";
            //sqlQuery = "alter table user_runes add CONSTRAINT sub4_constraint FOREIGN KEY (sub4) REFERENCES rune_stats(runestat) ON DELETE RESTRICT ON UPDATE RESTRICT";


            //rune grade
            //sqlQuery = "CREATE TABLE sql5410429.rune_grades ( runegrade INT(1) NOT NULL ) ENGINE = InnoDB;";
            //sqlQuery = "insert into rune_grades (runegrade) value (5), (6)";
            //sqlQuery = "alter table rune_grades add PRIMARY KEY (runegrade)";
            //sqlQuery = "alter table user_runes add CONSTRAINT grade_constraint FOREIGN KEY (grade) REFERENCES rune_grades(runegrade) ON DELETE RESTRICT ON UPDATE RESTRICT";

            getStatement().execute(sqlQuery);

        }catch (SQLException e){
            System.out.println("Error found: " + e);
        }
    }

    public void createRuneDatabase(){
        try{
            //String sqlQuery = "CREATE TABLE sql5410429.user_runes ( userid INT(4) NOT NULL , runeset VARCHAR(15) NOT NULL , runepos INT(1) NOT NULL , mainstat VARCHAR(5) NOT NULL ) ENGINE = InnoDB;";

            getStatement().execute(createRuneDatabaseQuery());

        }catch (SQLException e){
            System.out.println("Error found: " + e);
        }
    }
    private String createRuneDatabaseQuery(){
        return null;
    }

    public void addColumns(){

        try{

            //String sqlQuery = "ALTER TABLE `user_runes` ADD `grade` INT(1) NOT NULL AFTER `mainstat`";
            //String sqlQuery = "ALTER TABLE `user_runes` ADD `innate` INT(4) NOT NULL AFTER `grade`";
            //String sqlQuery = "ALTER TABLE user_runes ADD sub4 INT(4) NOT NULL AFTER sub3";
            //String sqlQuery = "ALTER TABLE `user_runes` DROP COLUMN `sub2`";

            //String sqlQuery = "ALTER TABLE user_runes ADD runeid INT(4) NOT NULL AFTER userid";
//            String sqlQuery = "ALTER TABLE user_runes change innate innate varchar(5) NULL";
//            String sqlQuery = "ALTER TABLE user_runes change innate_val innate_val INT(4) NULL AFTER innate";
            String sqlQuery = "alter table user_runes add sub4_val int(4) not null after sub4";

            getStatement().execute(sqlQuery);

            System.out.println("success");

        }catch(SQLException e){
            System.out.println("Error found: " + e);
        }


    }

    public boolean addRuneToUser(int user, Rune r){
        boolean success = false;
        try{
            if(!userHasRune(user, r)){
                String sqlQuery = addRuneSQLQuery(user, r);
                success = getStatement().execute(sqlQuery);
                System.out.printf("%s   ****rune added to user %d****\n", r, user);
            }
        }catch (SQLException e){
            System.out.println("Error found: " + e);
        }
        return success;
    }
    private String addRuneSQLQuery(int user, Rune r){
        //checkIfUserHasRune(user, r);
        //	userid	runeid	runegrade-runeset-position-innate-mainstat	sub0	val0	sub1	val1	sub2	val2	sub3	val3	sub4	val4
        String grade = r.getGradeString(), set = r.getSetString(), pos = r.getPositionString(), stat = r.getMainStat().getMainStatAttribute(), innate = r.getInnateString();
        StringBuilder s = new StringBuilder();
        s.append( String.format("INSERT INTO user_runes VALUES ('10', NULL, ") );
        s.append( String.format("'%s', '%s', '%s', '%s', '%s', ", grade, set, pos, innate, stat) );
        if(r.getRuneInnate()){
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(0).getSubStat(), r.getSubStats().get(0).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(1).getSubStat(), r.getSubStats().get(1).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(2).getSubStat(), r.getSubStats().get(2).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(3).getSubStat(), r.getSubStats().get(3).getSubValue()) );
            s.append( String.format("'%s', '%s' ", r.getSubStats().get(4).getSubStat(), r.getSubStats().get(4).getSubValue()) );
        }
        else{
            s.append("NULL, NULL, ");
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(0).getSubStat(), r.getSubStats().get(0).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(1).getSubStat(), r.getSubStats().get(1).getSubValue()) );
            s.append( String.format("'%s', '%s', ", r.getSubStats().get(2).getSubStat(), r.getSubStats().get(2).getSubValue()) );
            s.append( String.format("'%s', '%s' ", r.getSubStats().get(3).getSubStat(), r.getSubStats().get(3).getSubValue()) );
        }
        s.append(");");

        //System.out.println(s);

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
