package com.codecool.simpleSQLApp.io;

import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

import java.sql.ResultSet;
import java.sql.SQLException;

public class View {

    public void printTableFromDB(ResultSet resultSet) throws SQLException {
        System.out.println(FlipTableConverters.fromResultSet(resultSet));
    }

    public void printMenu(){
        String[] headers = {"Options from queries: "};
        String[][] data = {
                {"[1] See mentors' first and last names"},
                {"[2] See Miskolc mentors' nicknames"},
                {"[3] See Carol"},
                {"[4] See user with this - adipiscingenimmi.edu detail"},
                {"[5] See applicants with 54823 application code"},
                {"[6] Update Jemina's phone number"},
                {"[7] See Jemina's phone number"},
                {"[8] Delete users with this email suffix @mauriseu.net"},
                {"[0] Exit"},

        };
        System.out.println(FlipTable.of(headers,data));
    }
}
