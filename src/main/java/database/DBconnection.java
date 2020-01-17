package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create connection to Database using jdbc.
 */
public class DBconnection {

    static String url = "jdbc:mysql://projects-db.ewi."
           + "tudelft.nl:3306/projects_pManProject?serverTimezone=UTC";
    static String username = "pu_pManProject";
    static String password = "HEUGEA4u7zN1";


    /**
     * Creates connection to database in tu delft servers.
     */
    public static Connection getConnection() {
        Connection connect = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           if(connect == null) {
               connect = DriverManager.getConnection(url, username, password);
           }
            return connect;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}