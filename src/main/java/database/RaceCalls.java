package database;

import models.Race;

import java.sql.*;
import java.util.ArrayList;

public class RaceCalls {
    public static ArrayList<Race> RaceList(Connection con, String connectionString){
        ArrayList<Race> races = new ArrayList<>();
        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");


            String sql = "SELECT id, name FROM races";
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Race race = new Race(id, name);
                races.add(race);
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
        return races;
    }
}
