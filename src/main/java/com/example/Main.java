package com.example;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(Connection conn = DatabaseConnector.getConnection()) {
            System.out.println("Kết nối thành công");
        }catch (SQLException e){
            e.printStackTrace();
        }
        TaskManager tm = new TaskManager();
        tm.completedTask(1);
        tm.getAllTasks();
    }

}