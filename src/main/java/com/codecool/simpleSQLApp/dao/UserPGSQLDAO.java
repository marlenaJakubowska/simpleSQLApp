package com.codecool.simpleSQLApp.dao;

import com.codecool.simpleSQLApp.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class UserPGSQLDAO<T> implements IDao<T> {

    public final String TABLE;

    public UserPGSQLDAO(String table) {
        TABLE = table;
    }

    protected Connection getConnection() {
        PGConnector pgConnector = new PGConnector();
        return pgConnector.connect();
    }

    protected abstract PreparedStatement createPStatementForUpdate(T object, Connection connection);

    protected abstract PreparedStatement createPStatementForInsert(T object, Connection connection);

    abstract T create(ResultSet rs) throws SQLException;

    public List<T> getAllElements() {
        List<T> elements = new ArrayList<>();
        Connection connection = this.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + this.TABLE + ";");
            while (rs.next()) {
                elements.add(create(rs));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException d) {
                d.printStackTrace();
            }
            e.printStackTrace();
        }
        return elements;
    }

    public Optional<T> getElementById(Long id) {
        T element;
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + this.TABLE + " WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                element = create(rs);
                rs.close();
                preparedStatement.close();
                connection.close();
                return Optional.of(element);
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException d) {
                d.printStackTrace();
            }
            e.printStackTrace();
        }
        return Optional.empty();
    }

    protected boolean addObject(T object){
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = createPStatementForInsert(object, connection);
            executeAndClosePSandConnection(connection, preparedStatement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void executeAndClosePSandConnection(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    protected boolean editObject(T object){
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = createPStatementForUpdate(object, connection);
            executeAndClosePSandConnection(connection, preparedStatement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean deleteByID(Long id){
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + this.TABLE + " WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
