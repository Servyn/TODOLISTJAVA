package com.example;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try(Connection conn = DatabaseConnector.getConnection()) {
            System.out.println("Kết nối thành công");
        }catch (SQLException e){
            e.printStackTrace();
        }
        TaskManager tm = new TaskManager();
//        tm.searchTaskByTitle("java");
        List<Tasks> tasksList = tm.getAllTasks();
        for (Tasks tasks: tasksList){
            System.out.println(tasks.toString());
        }
    }

}