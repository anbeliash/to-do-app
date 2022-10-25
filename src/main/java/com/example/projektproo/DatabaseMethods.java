package com.example.projektproo;

import java.sql.*;
public class DatabaseMethods {
    private Statement statement;
    private Connection connection;

    public DatabaseMethods()
    {
        statement = DatabaseConnector.getConnection().getKey();
        connection = DatabaseConnector.getConnection().getValue();
    }

    public void insertTask(String title, String description, String category, String deadline, String isCompleted, String userId)
    {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into Tasks (Title, Description, Deadline, Category, IsCompleted, UserId)"
                            + " values (?, ?, ?, ?, ?, ?)");
            ps.setString(1,title);
            ps.setString(2,description);
            ps.setString(3,deadline);
            ps.setString(4,category);
            ps.setString(5,isCompleted);
            ps.setString(6, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTask(Integer id){
        try {
            PreparedStatement pst = connection.prepareStatement("delete from Tasks where TaskId = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editTask(String title, String description, String category, String deadline, String isCompleted, Integer taskId){
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "update Tasks set Title = ?, Description = ?, Deadline = ?, Category = ?, IsCompleted = ? where TaskId = ?");
            ps.setString(1,title);
            ps.setString(2,description);
            ps.setString(3,deadline);
            ps.setString(4,category);
            ps.setString(5,isCompleted);
            ps.setInt(6, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectTasks(String UserId)
    {
        ResultSet rset;
        try {
            rset = statement.executeQuery("Select * from Tasks WHERE UserId = " + UserId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rset;
    }

    public ResultSet filterTasks(String title, String isCompleted, String category, String UserId)
    {
        ResultSet rset;
        String sql = "Select * from Tasks where "
                + "Title = " + "'" + title + "'" + " AND "
                + "IsCompleted = " + isCompleted + " AND "
                + "Category = " + "'" + category + "'" + " AND "
                + "UserId = " + UserId;
        try {
            rset = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rset;
    }
}
