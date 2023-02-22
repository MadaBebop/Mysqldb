package it.develhope;

import java.util.ArrayList;
import java.sql.*;

public class DBConnection {

    public static void main(String[] args) {
        ArrayList<String> surnames = new ArrayList<String>();

        // db query tools
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // db connection parameters
            String url       = "jdbc:mysql://localhost:3306/sqldb";
            String user      = "developer";
            String password  = "developerpsw";

            // create a connection to the database
            connection = DriverManager.getConnection(url, user, password);
            //Statement inizialization
            statement = connection.createStatement();
            //sql query
            String sql= "SELECT last_name, first_name FROM students";
            //insertion query
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                surnames.add(resultSet.getString(2));
                //Stampa dei nomi
                System.out.println("Name : " + resultSet.getString(1)
                        + "\tLast Name : " + resultSet.getString(2));
            }

            //Stampa con arraylist
            System.out.println("ArrayList: \n");
            for(String student :surnames ){
                System.out.println(student);
            }

        }catch(SQLException sqle) {
            System.out.println( sqle.getMessage() );

        }finally{ //closing all db connections / statements
            try {if(connection != null) { connection.close();}}catch(SQLException ignored) {/*ignored*/}
            try {if(statement != null) { statement.close();}}catch(SQLException ignored) {/*ignored*/}
            try {if(resultSet != null) { resultSet.close();}}catch(SQLException ignored) {/*ignored*/}
        }

        }
    }