package org.example;

import java.sql.*;

public class Task {

    private int id;
    private String name;
    private boolean isComplete;

    public Task(String name, boolean isComplete, int id){
        this.name = name;
        this.isComplete = isComplete;
        this.id = id;
    }

    public Task(){

    }

    public Task(int id) {
        this.id = id;
    }

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void insertTask(){
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement insertStmt =
                    dbConnection.prepareStatement("INSERT INTO todo (task, done) VALUES (?, ?);");
            insertStmt.setString(1, this.name);
            insertStmt.setBoolean(2, (this.isComplete));
            int rows = insertStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveTasks(){
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            String query = "SELECT id, task, done FROM todo";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                //Display values
                String row = "ID: " + rs.getInt("id") +
                        " Task: " + rs.getString("task") +
                        " Done: " + rs.getBoolean("done") + "\n";
                System.out.print(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTask(){
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement updateStmt =
                    dbConnection.prepareStatement("update todo set done=true where task=? ");
            updateStmt.setString(1, this.name);
            int rows = updateStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(){
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            PreparedStatement deleteStmt =
                    dbConnection.prepareStatement("DELETE from todo where id=? ");
            deleteStmt.setInt(1, this.id);
            int rows = deleteStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        return "Task: " + this.name + "\nDone: " + (this.isComplete ? "1": "0");
    }
}