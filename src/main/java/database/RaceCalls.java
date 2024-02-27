package database;

import models.Race;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static database.DatabaseCalls.*;

public class RaceCalls {
    private static ArrayList<Race> races = new ArrayList<>();

    public static int GetRacesSize(){
        return races.size();
    }

    public static ArrayList<Race> GetRaces(){
        return races;
    }

    public static String GetRaces(int ID){
        return races.get(ID).getName();
    }

    public static void CreateRace(Connection con, String connectionString){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        String name = InputStringData("name", sc);
        columns.add("name");
        values.add(name);
        InsertIntoCall(con, connectionString, "races", columns, values);
        RaceList(con, connectionString);
    }

    public static void DeleteRace(Connection con, String connectionString){
        System.out.println("Enter Race ID:");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        RemoveCall(con, connectionString, "races", id);
        RaceList(con, connectionString);
    }

    public static void RaceList(Connection con, String connectionString){
        races = new ArrayList<Race>();
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
    }
}
