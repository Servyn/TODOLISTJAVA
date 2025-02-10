package com.example;

import java.sql.*;

public class TaskManager {
    public void addTask(String title, String description){
        try(Connection conn = DatabaseConnector.getConnection()) {
            String sql = "INSERT INTO tasks (title, description) VALUES (? , ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,title);
            pstmt.setString(2,description);

            int rowInserted = pstmt.executeUpdate();
            if (rowInserted > 0 ){
                try (ResultSet rs = pstmt.getGeneratedKeys();){
                    if (rs.next()){
                        int generatedId = rs.getInt(1);
                        System.out.println("thêm task thành công");
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void getAllTasks(){
        try(Connection conn = DatabaseConnector.getConnection()) {
            int id;
            String title;
            String description;
            boolean status;
            Timestamp createdAt;
            String sql = "SELECT * FROM tasks;";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                id = rs.getInt("id");
                title = rs.getString("title");
                description = rs.getString("description");
                status = rs.getBoolean("status");
                createdAt = rs.getTimestamp("created_at");
                System.out.println("ID: " + id);
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("Status: " + (status ? "Hoàn thành" : "Chưa hoàn thành"));
                System.out.println("Created At: " + createdAt);
                System.out.println("--------------------------------");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateTask(int id, String title, String description, boolean status){
        try(Connection conn = DatabaseConnector.getConnection()) {
            String sql = "UPDATE tasks SET title = ?, description = ?, status = ? WHERE id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setBoolean(3, status);
            pstmt.setInt(4, id);
            int rowUpdated = pstmt.executeUpdate();
            if (rowUpdated > 0 ){
                System.out.println("update thành công");
            } else {
                System.out.println("Không tìm thấy task có id : " + id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteTask(int id){
        try(Connection conn = DatabaseConnector.getConnection()) {
            String sql = "DELETE FROM tasks WHERE id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowDeleted = pstmt.executeUpdate();
            if (rowDeleted > 0 ){
                System.out.println("delete thành công");
            } else
                System.out.println("delete không thành công");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void completedTask(int id ){
        try(Connection conn = DatabaseConnector.getConnection()) {
            String sql = "UPDATE tasks SET status = 1 WHERE id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowCompleted = pstmt.executeUpdate();
            if (rowCompleted > 0 ){
                System.out.println("Task có ID : " + id + " đã hoàn thành");
            } else {
                System.out.println("Không tìm thấy task có id : " + id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
