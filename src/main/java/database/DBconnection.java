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



        public static ResultSet prepAndExecuteQuery(int rank) {

            ResultSet rs = null;
            String query = String.format("SELECT `username`, `score`FROM `ScoreBoard` Emp1 WHERE (%d) = (SELECT COUNT(DISTINCT(Emp2.score)) FROM `ScoreBoard` Emp2 WHERE Emp2.score > Emp1.score)", rank);

            try {
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                //ps.setInt(1, rank);
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;
        }

        public static String fromResultsetToString(ResultSet rs){
            String result = "";
            ResultSetMetaData rsmd = null;
            try {
                rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        result += columnValue + " ";
                        System.out.println(result);


                    }
                    System.out.println("");
                    while(rs.next()) {
                        rs.next();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
