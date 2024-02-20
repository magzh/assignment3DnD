package database;

import models.Battle;
import models.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CombatCalls {
    public static void BattleMode(Connection con, String connectionString){
        System.out.println("Select your command:\n" +
                "1 View past battles\n" +
                "2 Pit two fighters\n" +
                "3 Return to Main Menu");
        Scanner sc = new Scanner(System.in);
        int mode = sc.nextInt();
        switch (mode){
            case 1:
                SearchBattle(con,connectionString);
                break;
            case 2:
                PitBattle(con, connectionString);
                break;
            default:
                return;
        }
    }
    public static void SearchBattle(Connection con, String connectionString){
        ArrayList<Hero> heroes = new ArrayList<>();
        ArrayList<Battle> battles = new ArrayList<>();
        String search = " ";
        System.out.println("Select your command:\n" +
                "1 Search by fighter ID\n" +
                "2 Show all\n" +
                "3 Return to Main Menu");
        Scanner sc = new Scanner(System.in);
        int mode = sc.nextInt();
        int searchid = 0;
        String searchname = "";
        switch (mode){
            case 1:
                System.out.println("Input fighter ID:");
                searchid = sc.nextInt();
                search = " WHERE fighter1 = '" + searchid + "'" + " OR fighter2 = '" + searchid + "'";
                break;
            case 2:
                search = "";
                break;
            default:
                return;
        }
        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");


            String sql = "SELECT id, name, fighter1, fighter2, winner, date FROM battles" + search;
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int fighter1 = rs.getInt("fighter1");
                int fighter2 = rs.getInt("fighter2");
                boolean winner = rs.getBoolean("winner");
                String date = "";

                Battle battle = new Battle(id, name, fighter1, fighter2, winner, date);
                battles.add(battle);
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

        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");


            String sql = "SELECT id, name, race, strength, agility, intelligence, wins, losses FROM heroes";
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

                if (id == searchid){
                    searchname = name;
                }

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
        switch (mode){
            case 1:
                System.out.println("All battles involving " + searchname +":");
                break;
            case 2:
                System.out.println("All Battles:");
                break;
            default:
                return;
        }
        for (Battle battle : battles) {
            System.out.println(battle);
        }
    }
    public static void PitBattle(Connection con, String connectionString){
        ArrayList<Hero> heroes = new ArrayList<>();
        ArrayList<Battle> battles = new ArrayList<>();
        String search = " ";
        System.out.println("Select your first fighter ID:");
        Scanner sc = new Scanner(System.in);
        int id1 = -1;
        int id2 = -1;
        boolean running = true;
        while (running){
            id1 = sc.nextInt();
            search = " id = '" + id1 + "'";
            try {
                con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");
                String requestedName = "";

                String sql = "SELECT id, name, race, strength, agility, intelligence, wins, losses FROM heroes WHERE" + search;
                Statement stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int race = rs.getInt("race");
                    int strength = rs.getInt("strength");
                    int agility = rs.getInt("agility");
                    int intelligence = rs.getInt("intelligence");
                    int wins = rs.getInt("wins");
                    int losses = rs.getInt("losses");
                    running = false;

                    Hero hero = new Hero(id, name, race, strength, agility, intelligence, wins, losses);
                    heroes.add(hero);
                }
                else {
                    System.out.println("Invalid fighter ID, input a valid ID:");
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
        System.out.println("Select your second fighter ID:");
        running = true;
        while (running){
            id2 = sc.nextInt();
            if (id1 == id2){
                id2 =   -1;
            }
            search = " id = '" + id2 + "'";
            try {
                con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");
                String requestedName = "";

                String sql = "SELECT id, name, race, strength, agility, intelligence, wins, losses FROM heroes WHERE" + search;
                Statement stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int race = rs.getInt("race");
                    int strength = rs.getInt("strength");
                    int agility = rs.getInt("agility");
                    int intelligence = rs.getInt("intelligence");
                    int wins = rs.getInt("wins");
                    int losses = rs.getInt("losses");
                    running = false;

                    Hero hero = new Hero(id, name, race, strength, agility, intelligence, wins, losses);
                    heroes.add(hero);
                }
                else {
                    System.out.println("Invalid fighter ID, input a valid ID:");
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
        boolean winner;
        Random random = new Random();
        int battlemode = random.nextInt(0,3);
        switch (battlemode){
            case 0:
                if (heroes.get(0).getStrength() > heroes.get(1).getStrength()){
                    winner = true;
                }
                else {
                    winner = false;
                }
                break;
            case 1:
                if (heroes.get(0).getAgility() > heroes.get(1).getAgility()){
                    winner = true;
                }
                else {
                    winner = false;
                }
                break;
            default:
                if (heroes.get(0).getIntelligence() > heroes.get(1).getIntelligence()){
                    winner = true;
                }
                else {
                    winner = false;
                }
        }

        if (winner){
            System.out.println(heroes.get(0).getName() + " has won the battle");
        }
        else {
            System.out.println(heroes.get(1).getName() + " has won the battle");
        }

        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");
            String requestedName = "";

            String sql = "INSERT INTO battles (name, fighter1, fighter2, winner)\n" +
                    "VALUES('"+ "IndividualDuel" +"', '"+id1+"', '"+id2+"', '"+winner+"');";
            Statement stmt = con.createStatement();

            stmt.executeQuery(sql);


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
