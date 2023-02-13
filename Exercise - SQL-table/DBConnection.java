package it.develhope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // db parameters
            String url       = "jdbc:mysql://localhost:3306/sqldb";
            String user      = "developer";
            String password  = "developerpsw";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("db connection established");

        } catch(SQLException sqle) {
            System.out.println( sqle.getMessage() );

        } finally {
            try{
                if(conn != null){
                    conn.close();
                    System.out.println("db connection closed");
                }
            }catch(SQLException sqle){
                System.out.println("db closing connection error!");
                System.out.println(sqle.getMessage());
            }
        }
    }

}