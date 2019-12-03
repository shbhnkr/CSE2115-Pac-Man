package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create connection to Database using jdbc.
 */
public class DBConnection {

    transient String url = "jdbc:mysql://projects-db.ewi.tudelft.nl:3306/projects_pManProject?serverTimezone=UTC";
    transient String username = "pu_pManProject";
    transient String password = "HEUGEA4u7zN1";


    transient private Connection connection;
    private static DBConnection single_instance = null;

    /**
     * Creates connection to database in tu delft servers.
     */
    private DBConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.connection = connection;
    }

    /**
     * Returns connection made to database.
     *
     * @return - Connection object to database
     */
    public static Connection getConnection() {
        if (single_instance == null) {
            single_instance = new DBConnection();
        }

        return single_instance.connection;
    }
}