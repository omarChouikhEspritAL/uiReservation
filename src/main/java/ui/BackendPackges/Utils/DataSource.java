package com.example.journeywiseguijaxafx.BackendPackges.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
    private Connection con;
    private static DataSource dataSource;
    private static String url = "jdbc:mysql://localhost:3306/journey_wise";
    private static String user = "root";
    private static String pwd = "";
    private static Statement ste;

    private DataSource()  {
        try {
            System.out.println("Connecting to Database ...");
            con = DriverManager.getConnection(url,user,pwd);
            System.out.println("Connected!");
        }
        catch (SQLException ex)  {
            System.out.println(ex);
        }

    }

    public Connection getCon() {
        return con;
    }

    public static DataSource getInstance() {
        if (dataSource == null)
            dataSource = new DataSource();
        return dataSource;
    }

}
