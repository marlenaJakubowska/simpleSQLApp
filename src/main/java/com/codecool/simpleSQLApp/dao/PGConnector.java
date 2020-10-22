package com.codecool.simpleSQLApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PGConnector {

    private final String user = "marlena";
    private final String password = "password";
    private static final String url = "jdbc:postgresql://localhost:5432/simpleSQLAppDB";

    protected Connection connection;
    protected Statement statement;

    public void connect() {
        connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to DB");
            statement = connection.createStatement();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Not connected");
        }
    }
}
