package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection{
    private final String url;
    private final int port;
    private final String dbName;
    private Connection connection;

    private static DBConnection instance;

    private DBConnection() throws SQLException {
        this.dbName = "tododb";
        this.port = 5432;
        this.url = "jdbc:postgresql://localhost:" + Integer.toString(this.port) + "/" + this.dbName;
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","tiger");
        props.setProperty("ssl","false");
        this.connection = DriverManager.getConnection(url, props);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if(instance == null){
            instance = new DBConnection();
        }
        else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }
}