package com.codecool.simpleSQLApp.dao;

import com.codecool.simpleSQLApp.models.Applicant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ApplicantDAO extends UserPGSQLDAO<Applicant> {

    public ApplicantDAO() {
        super("applicants");
    }

    @Override
    protected PreparedStatement createPStatementForUpdate(Applicant applicant, Connection connection) {
        long id = applicant.getId();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE applicants SET " +
                    "first_name=?, last_name=?, phone_number=?, email=?, city=?, application_code=? " +
                    " WHERE id = ?");
            preparedStatement = setApplicantValues(applicant, preparedStatement);
            preparedStatement.setLong(8, applicant.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    private PreparedStatement setApplicantValues(Applicant applicant, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, applicant.getFirstName());
        preparedStatement.setString(2, applicant.getLastName());
        preparedStatement.setString(3, applicant.getPhoneNumber());
        preparedStatement.setString(4, applicant.getEmail());
        preparedStatement.setInt(5, applicant.getApplicationCode());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement createPStatementForInsert(Applicant applicant, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO applicants" +
                    "(first_name, last_name, phone_number, email, application_code ) VALUES " +
                    "(?, ?, ?, ?, ?)");
            preparedStatement = setApplicantValues(applicant, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    Applicant create(ResultSet rs) throws SQLException {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String phoneNumber = rs.getString("phone_number");
            String email = rs.getString("email");
            int applicationCode = rs.getInt("application_code");
            return new Applicant(firstName, lastName, phoneNumber, email, applicationCode);
    }

    @Override
    public boolean add(Applicant applicant) {
        return addObject(applicant);
    }

    @Override
    public boolean edit(Applicant applicant) {
        return editObject(applicant);
    }

    @Override
    public boolean remove(long id) {
        return deleteByID(id);
    }

    @Override
    public List<Applicant> getAll(){
        return getAllElements();
    }

    @Override
    public Optional<Applicant> get(long id) {
        return getElementById(id);
    }

    public void getCarol() {
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT concat(first_name, ' ',  last_name, ' ' , phone_number) AS full_name from applicants WHERE first_name='Carol';");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(
                        rs.getString("full_name"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAdipiscingenimmiSuffixUser() {
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT concat(first_name, ' ',  last_name, ' ' , phone_number) AS full_name from applicants WHERE email LIKE '%adipiscingenimmi.edu';");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(
                        rs.getString("full_name"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getByApplicationCode(){
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * from applicants WHERE application_code=54823;");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Applicant a = create(rs);
                System.out.println(a.toString());
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateJemina(){
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE applicants SET phone_number='003670/223-7459' WHERE first_name='Jemima' AND last_name='Foreman';");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeMauriseuSuffixUsers(){
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE from applicants WHERE email LIKE '%@mauriseu.net';");
            preparedStatement.executeUpdate();
            System.out.println("Deleted");
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
