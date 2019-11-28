package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
making a class that can access a database
 */
public class DBConnection {
    public static Connection getConnection() {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*
            access the database with the servername/databaseName, username and password
             */
            Connection connection = DriverManager.getConnection("jdbc:mysql://projects-db.ewi.tudelft.nl/projects_pManProject", "pu_pManProject\n", "HEUGEA4u7zN1\n");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("");
        }
        return null;

    }

}
