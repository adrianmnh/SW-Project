package classes;

import runes.Rune;
import runes.RuneCollection;

import java.util.ArrayList;

public class User {

    private String username;
    private char[] password;


    public User(String u, char[] p){

        this.username = u;
        this.password = p;
    }
    public String getUsername(){
        return this.username;
    }
    public char[] getPassword(){
        return this.password;
    }


}
