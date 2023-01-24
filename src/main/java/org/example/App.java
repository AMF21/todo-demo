package org.example;

import java.sql.SQLException;
public class App
{
    public static void main(String[] args) {
        try {
            DBConnection db = DBConnection.getInstance();

            Task x= new Task();

            // Insert
            //Task t = new Task("Abdulaziz Alfozan", false);
            //t.insertTask();

            // Delete
            //Task t= new Task(id);
            //t.deleteTask();

            //update
            //Task t= new Task("task name");
            //t.updateTask();

            // Retrieve all tasks
            x.retrieveTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}