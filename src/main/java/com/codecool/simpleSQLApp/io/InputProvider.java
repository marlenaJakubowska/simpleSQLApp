package com.codecool.simpleSQLApp.io;

import com.jakewharton.fliptables.FlipTableConverters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputProvider {

    Scanner scanner = new Scanner(System.in);

    public void printTableFromDB(ResultSet resultSet) throws SQLException {
        System.out.println(FlipTableConverters.fromResultSet(resultSet));
    }

    public String takeUserInput(String message) {
        String userInput;
        Scanner scan = new Scanner(System.in);
        boolean invalidInput;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9@.]");

        do{
            System.out.println(message);
            userInput = scan.nextLine();
            invalidInput = pattern.matcher(userInput).find();

            if(invalidInput){
                System.out.println("That's not a valid input");
            }
        }while (invalidInput);
        return userInput;
    }
}
