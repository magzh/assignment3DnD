package database;

import models.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static database.DatabaseCalls.*;
import static database.RaceCalls.*;

public class HeroCalls {
    public static void CreateHero(Connection con, String connectionString){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        String name = InputStringData("name", sc);
        columns.add("name");
        values.add(name);
        //int race = InputIntData("race", sc);
        System.out.println("Input Race Name:");
        String raceName = sc.next();
        int race = 0;
        for (int i = 0; i < GetRacesSize(); i++) {
            if (GetRaces(i).equals(raceName)){
                race = i;
            }
        }
        columns.add("race");
        values.add(String.valueOf(race));
        int strength = InputIntData("strength", sc);
        columns.add("strength");
        values.add(String.valueOf(strength));
        int agility = InputIntData("agility", sc);
        columns.add("agility");
        values.add(String.valueOf(agility));
        int intelligence = InputIntData("intelligence", sc);
        columns.add("intelligence");
        values.add(String.valueOf(intelligence));
        columns.add("wins");
        values.add("0");
        columns.add("losses");
        values.add("0");
        InsertIntoCall(con, connectionString, "heroes", columns, values);
    }



    public static void SearchHero(Connection con, String connectionString){
        ArrayList<Hero> heroes = new ArrayList<>();
        String search = " ";
        System.out.println("Select your command:\n" +
                "1 Search by ID\n" +
                "2 Search by Name\n" +
                "3 Search by Race\n" +
                "4 Return to Main Menu");
        Scanner sc = new Scanner(System.in);
        int mode = sc.nextInt();
        switch (mode){
            case 1:
                System.out.println("Input ID:");
                int id = sc.nextInt();
                search = " id = '" + id + "'";
                break;
            case 2:
                System.out.println("Input Name:");
                String name = sc.next();
                search = " name = '" + name + "'";
                break;
            case 3:
                System.out.println("Input Race Name:");
                String raceName = sc.next();
                int race = -1;
                for (int i = 0; i < GetRacesSize(); i++) {
                    if (GetRaces(i).equals(raceName)){
                        race = i;
                    }
                }
                search = " race = '" + race + "'";
                break;
            default:
                return;
        }
        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");


            String sql = "SELECT id, name, race, strength, agility, intelligence, wins, losses FROM heroes WHERE" + search;
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int race = rs.getInt("race");
                int strength = rs.getInt("strength");
                int agility = rs.getInt("agility");
                int intelligence = rs.getInt("intelligence");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");

                Hero hero = new Hero(id, name, race, strength, agility, intelligence, wins, losses);
                heroes.add(hero);
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
        if (heroes.size()==0){
            System.out.println("Hero not found");
        }

        for (int i = 0; i < heroes.size(); i++) {
            System.out.println(heroes.get(i).toString());
            GetRecord(con, connectionString, heroes.get(i).getId());
        }
    }

    public static void ListAllHeroes(Connection con, String connectionString){
        ArrayList<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("name");
        columns.add("race");
        columns.add("strength");
        columns.add("agility");
        columns.add("intelligence");
        columns.add("wins");
        columns.add("losses");
        ListTableCall(con, connectionString, "heroes", columns);
        // String sql = "SELECT id, name, race, strength, agility, intelligence, wins, losses FROM heroes";
    }

    public static void UpdateHero(Connection con, String connectionString, int id, boolean won){
        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");

            String sql2 = "";

            if (won){
                sql2 = "UPDATE heroes\n" + "SET wins = wins + 1\n" + "WHERE id = " + id + ";";
            }
            else {
                sql2 = "UPDATE heroes\n" + "SET losses = losses + 1\n" + "WHERE id = " + id + ";";
            }

            Statement stmt = con.createStatement();

            stmt.execute(sql2);


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

    public static void GetRecord(Connection con, String connectionString, int id){
        ArrayList<String> columns = new ArrayList<>();
        columns.add("id");
        columns.add("name");
        columns.add("race");
        columns.add("strength");
        columns.add("agility");
        columns.add("intelligence");
        columns.add("wins");
        columns.add("losses");

        ArrayList<Hero> list = ListTableSend(con, connectionString, "heroes", columns);

        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");

            String sql2 = "SELECT\n" +
                    "    heroes.id,\n" +
                    "    heroes.name,\n" +
                    "    fighter1,\n" +
                    "    fighter2,\n" +
                    "    winner\n" +
                    "FROM\n" +
                    "    heroes\n" +
                    "INNER JOIN battles \n" +
                    "   ON heroes.id = fighter1 OR heroes.id = fighter2\n" +
                    "WHERE heroes.id = " + id + ";";

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql2);

            String opponent = "";

            while (rs.next()) {
                if (rs.getInt("id") == rs.getInt("fighter1")){
                    if (rs.getBoolean("winner")){
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getId() == rs.getInt("fighter2")){
                                opponent = list.get(i).getName();
                            }
                        }
                        System.out.println(rs.getString("name") + " has won a battle against " + opponent);
                    }
                    else {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getId() == rs.getInt("fighter2")){
                                opponent = list.get(i).getName();
                            }
                        }
                        System.out.println(rs.getString("name") + " has lost a battle against " + opponent);
                    }
                }else{
                    if (rs.getBoolean("winner")){
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getId() == rs.getInt("fighter1")){
                                opponent = list.get(i).getName();
                            }
                        }
                        System.out.println(rs.getString("name") + " has lost a battle against " + opponent);
                    }
                    else {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getId() == rs.getInt("fighter1")){
                                opponent = list.get(i).getName();
                            }
                        }
                        System.out.println(rs.getString("name") + " has won a battle against " + opponent);
                    }
                }
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

    public static void DeleteHero(Connection con, String connectionString){
        System.out.println("Enter Hero ID:");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        RemoveCall(con, connectionString, "heroes", id);
    }
}
