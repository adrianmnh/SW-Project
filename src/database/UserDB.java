package database;

import classes.User;
//import classes.User;

import java.sql.*;
import java.util.Arrays;

public class UserDB extends Database{
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public UserDB() {
        super();
        setTable("GameTool.Account");
    }
    public void getData(){
        try{
            String sql = "select * FROM GameTool.Account";
            rs = st.executeQuery(sql);
            System.out.println("Data from online Database :");
            while(rs.next()){
//                String name = rs.getString("username");
//                String area = rs.getString("password");
//                String pin = rs.getString("email");
//                System.out.println("username:"+name+"\n"+"password :"+area+"\n"+"email:"+pin+"\n");

            }

        }catch(Exception ex){
            System.out.println("Error is found :"+ex);
        }
    }

    public static void main(String[] args) {
        UserDB connct = new UserDB();
        connct.getData();
    }

    private void addNewUser(){
    }

    public int userExists(User u){

        try{
            System.out.println("Checking user credentials");
            ResultSet rs = getStatement().executeQuery(SQLUserSearchQuery());

//            printResultSet(rs);

            while(rs.next()){
                if(rs.getObject(2).equals(u.getUsername())){
                    System.out.printf("username: '%s' found\n", u.getUsername());
                    if(Arrays.equals(rs.getObject(3).toString().toCharArray(),u.getPassword())){
                        System.out.println("username and passwordmatch!");

                        System.out.println("user id: " + rs.getObject(1).toString());
                        return Integer.parseInt(rs.getObject(1).toString());
                        //return true;
                    }
                    else{
                        System.out.printf("incorrect password\n");
                    }
                }
            }
            System.out.printf("username: %s 404 NOT FOUND\n",u.getUsername());
        }catch(Exception e){
            System.out.println("Error found. " + e);
            return -404;
            //return false;
        }
        return -404;
        //return false;

    }
    private String SQLUserSearchQuery(){
        String sql = "SELECT * FROM GameTool.Account;";
        return sql;
    }








}