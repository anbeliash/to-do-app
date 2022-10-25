package com.example.projektproo;
import javafx.util.Pair;

import java.sql.*;

public class DatabaseConnector {

    static public Connection databaseLink;
    static final private String dbUrl = "jdbc:mysql://localhost:3306/TODO";
    static final private String username = "root";
    static final private String password = "zaq1XSW@";

    public static Statement statement;


    public static Pair<Statement,Connection> getConnection() {
        try{
            databaseLink = DriverManager.getConnection(dbUrl, username, password);
            statement = databaseLink.createStatement();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new Pair<Statement,Connection>(statement, databaseLink);
    }
}
