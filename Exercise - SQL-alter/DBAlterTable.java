package it.develhope;
import java.sql.*;

public class DBAlterTable {
    public static void main(String[] args) {
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
            //Statement initialization
            statement = connection.createStatement();

            //1st sql query
            String sql = "ALTER TABLE students ADD country VARCHAR(40) ";
            statement.executeUpdate(sql);

            //2nd sql queries (update 4 students)
            String sql2 = "UPDATE students SET country = 'italy' WHERE student_id = 1 ";
            String sql3 = "UPDATE students SET country = 'germany' WHERE  student_id = 2";
            statement.executeUpdate(sql2); //n1
            statement.executeUpdate(sql3);//n2
            sql2 = "UPDATE students SET country = 'italy' WHERE student_id = 3";
            sql3 = "UPDATE students SET country = 'germany' WHERE student_id = 4";
            statement.executeUpdate(sql2); //n3
            statement.executeUpdate(sql3); //n4

        }catch(SQLException sqle) {
            System.out.println( sqle.getMessage() );

        }finally{ //closing all db connections / statements
            try {if(connection != null) { connection.close();}}catch(SQLException ignored) {/*ignored*/}
            try {if(statement != null) { statement.close();}}catch(SQLException ignored) {/*ignored*/}
            try {if(resultSet != null) { resultSet.close();}}catch(SQLException ignored) {/*ignored*/}
        }

    }
}

