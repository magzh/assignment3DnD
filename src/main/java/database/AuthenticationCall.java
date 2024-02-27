package database;

import java.sql.*;

public class AuthenticationCall {
    public static boolean PasswordCheck(Connection con, String connectionString, String pw){
        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");


            String sql = "SELECT id, name FROM lore WHERE name = '" + pw + "'";
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()){
                return true;
            }
        } catch (SQLException e){
            System.out.println("connection error: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("could not close the connection: " + e.getMessage());
                }
            }
        }
        return false;
    }
}
