package com.example.freshly;


import java.sql.*;

public class database {

    public static Connection connectDB() {

        try {

            Class.forName("com.mysql.jdbc.Driver");


            Connection connect =  DriverManager.getConnection("jdbc:mysql://localhost:3307/Freshly", "root", ""); // LINK YOUR DATABASE
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public static void main(String[] args) {
        Connection connection = database.connectDB();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO mtudent (id) VALUES (?)");
            statement.setString(1,"محمدجواد");
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }*/

}
