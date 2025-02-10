package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    List<Tasks> tasksList = new ArrayList<>();
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
    public List<Tasks> getAllTasks(){
        List<Tasks> tasksList = new ArrayList<>();
        String sql = "SELECT * FROM tasks;";

        try(Connection conn = DatabaseConnector.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tasks task = new Tasks(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getBoolean("status"),
                        rs.getTimestamp("created_at")
                );
                tasksList.add(task);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tasksList;
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
    public void searchTaskByTitle(String keyword ){
        try(Connection conn = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM tasks WHERE title LIKE ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                boolean status = rs.getBoolean("status");
                Timestamp createdAt = rs.getTimestamp("created_at");
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
}
