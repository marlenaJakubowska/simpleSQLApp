package com.codecool.simpleSQLApp.dao;

import com.codecool.simpleSQLApp.models.Mentor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MentorDAO extends UserPGSQLDAO<Mentor>{

    public MentorDAO() {
        super("mentors");
    }

    @Override
    protected PreparedStatement createPStatementForUpdate(Mentor mentor, Connection connection) {
        long id = mentor.getId();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE mentors SET " +
                    "first_name=?, last_name=?, nick_name=?, phone_number=?, email=?, city=?, favourite_number=? " +
                    " WHERE id = ?");
            preparedStatement = setMentorValues(mentor, preparedStatement);
            preparedStatement.setLong(8, mentor.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    private PreparedStatement setMentorValues(Mentor mentor, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, mentor.getFirstName());
        preparedStatement.setString(2, mentor.getLastName());
        preparedStatement.setString(3, mentor.getNickName());
        preparedStatement.setString(4, mentor.getPhoneNumber());
        preparedStatement.setString(5, mentor.getEmail());
        preparedStatement.setString(6, mentor.getCity());
        preparedStatement.setInt(7, mentor.getFavouriteNumber());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement createPStatementForInsert(Mentor mentor, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO mentors" +
                    "(id, first_name, last_name, nick_name, phone_number, email, city, favourite_number ) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement = setMentorValues(mentor, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    Mentor create(ResultSet rs) throws SQLException {
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String phoneNumber = rs.getString("phone_number");
        String email = rs.getString("email");
        String nickName = rs.getString("nick_name");
        String city = rs.getString("city");
        int favouriteNumber = rs.getInt("favourite_number");
        return new Mentor(firstName, lastName, phoneNumber, email, nickName, city, favouriteNumber);
    }


    @Override
    public boolean add(Mentor mentor) {
        return addObject(mentor);
    }

    @Override
    public boolean edit(Mentor mentor) {
        return editObject(mentor);
    }

    @Override
    public boolean remove(long id) {
        return deleteByID(id);

    }

    @Override
    public List<Mentor> getAll() {
        return getAllElements();
    }

    @Override
    public Optional<Mentor> get(long id) {
        return getElementById(id);
    }

    public void getNameAndSurname(){
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT first_name, last_name FROM " + this.TABLE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("first_name") + "|" + rs.getString("last_name"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getMiskolcMentorsNicknames(){
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT nick_name FROM mentors WHERE city='Miskolc'" );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nick_name"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
