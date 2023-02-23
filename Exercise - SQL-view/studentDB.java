package it.develhope;

import java.sql.*;
import java.util.ArrayList;
import java.util.stream.*;

public class studentDB {


    public static void main(String[] args) {
        ArrayList<Student> italianStudents = new ArrayList<>();
        ArrayList<Student> germanStudents = new ArrayList<>();

        //Connection related variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try { //Connectiong to db
            String url = "jdbc:mysql://localhost:3306/sqldb";
            String user = "developer";
            String password = "developerpsw";

            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            //first view creation
            String sql = "CREATE VIEW italian_students AS SELECT last_name, first_name FROM students WHERE country = 'italy'";
            statement.execute(sql);
            //second view creation
            sql = "CREATE VIEW german_students AS SELECT last_name, first_name FROM students WHERE country = 'germany'";
            statement.execute(sql);

            //Adding italian view table to arrayList
            resultSet = statement.executeQuery("SELECT * FROM italian_students");
            while (resultSet.next()){
                //Aggiungo lo studente all'arrayList
                italianStudents.add(
                        new Student(
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name")
                        )
                );
            }

            //Adding german students view table to ArrayList
            resultSet = statement.executeQuery("SELECT * FROM german_students");
            while (resultSet.next()){
                //Aggiungo lo studente all'arrayList
                germanStudents.add(
                        new Student(
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name")
                        )
                );
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());

        } finally {
            //closing db connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ignored) {/*ignored*/}
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ignored) {/*ignored*/}
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ignored) {/*ignored*/}
        }
    }
}
