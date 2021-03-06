package database;

import java.sql.*;

/**
 * Create connection to Database using jdbc.
 */
public class DBconnection {

    static String url = "jdbc:mysql://projects-db.ewi."
            + "tudelft.nl:3306/projects_pManProject?serverTimezone=UTC";
    static String username = "pu_pManProject";
    static String password = "HEUGEA4u7zN1";
    public static transient ResultSet rs = null;
    public static transient ResultSetMetaData rsmd = null;
    public static transient int columnsNumber;
    public static transient Connection conn;


    /**
     * Creates connection to database in tu delft servers.
     */
    public static Connection getConnection() {
        Connection connect = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connect == null) {
                connect = DriverManager.getConnection(url, username, password);
            }
            return connect;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * gets the results for the leader board from the database.
     * @param rank the earned rank
     * @return the results for the leader board.
     */
    public static ResultSet prepAndExecuteQuery(int rank) {

        String query = String.format("SELECT `username`, `score`FROM `ScoreBoard` Emp1 WHERE (%d)"
                + " = (SELECT COUNT(DISTINCT(Emp2.score)) FROM `ScoreBoard` Emp2"
                + " WHERE Emp2.score > Emp1.score)", rank);

        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            //ps.setInt(1, rank);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * converts the result set into a user friendly format.
     * @param rs the result set to convert
     * @return string representation of the result set.
     */
    public static String fromResultsetToString(ResultSet rs) {
        String result = "";

        try {
            rsmd = rs.getMetaData();
            columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    result += columnValue;
                    if (i < columnsNumber) {
                        result += "   Score: ";
                    }
                    //System.out.println(result);


                }
                System.out.println("");
                while (rs.next()) {
                    rs.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
