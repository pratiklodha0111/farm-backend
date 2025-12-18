package com.farm;

import java.sql.*;

public class EC2MySQLConnection {

    // Database connection details
    static final String DB_URL = "jdbc:mysql://3.15.196.217:3306/farmdb";
    static final String USER = "farmuser";
    static final String PASS = "farmpassword";

    public static void main(String[] args) {
        System.out.println("---- MySQL JDBC Connection Testing ----");
        Connection connection = null;
        try {
            // Register JDBC driver (optional for modern JDBC versions)
            // Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection successful!");
            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("DB Product: " + metaData.getDatabaseProductName());
            System.out.println("DB Version: " + metaData.getDatabaseProductVersion());
            System.out.println("Driver Name: " + metaData.getDriverName());
            System.out.println("Driver Version: " + metaData.getDriverVersion());

            ResultSet tables = metaData.getTables(
                    "farmdb",   // catalog
                    null,
                    "%",        // table name pattern
                    new String[]{"TABLE"}
            );

            System.out.println("\n---- Tables in farmdb ----");
            while (tables.next()) {
                System.out.println(tables.getString("TABLE_NAME"));
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console for details.");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
