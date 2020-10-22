package com.codecool.simpleSQLApp;

import com.codecool.simpleSQLApp.dao.PGConnector;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        PGConnector connector = new PGConnector();
    }



}
