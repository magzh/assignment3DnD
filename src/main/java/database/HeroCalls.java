package database;

import models.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeroCalls {
    public static void CreateHero(Connection con, String connectionString){
        Scanner sc = new Scanner(System.in);
        String name = InputStringData("name", sc);
        int race = InputIntData("race", sc);
        int strength = InputIntData("strength", sc);
        int agility = InputIntData("agility", sc);
        int intelligence = InputIntData("intelligence", sc);
        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");
            String requestedName = "";

            String sql = "INSERT INTO heroes (name, race, strength, agility, intelligence, wins, losses)\n" +
                    "VALUES('"+name+"', '"+race+"', '"+strength+"', '"+agility+"', '"+intelligence+"', '0', '0');";
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

    private static String InputStringData (String dataName, Scanner sc){
        String answer = "";
        boolean correctInput = true;
        while (correctInput){
            System.out.println("Give hero their " + dataName + ":");
            answer = sc.nextLine();
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(answer);
            correctInput = m.find();
            if (correctInput){
                System.out.println("Do not use special characters");
            }
        }
        return  answer;
    }

    private static int InputIntData (String dataName, Scanner sc){
        int answer = 0;
        boolean correctInput = true;
        do {
            try {
                System.out.println("Give hero their " + dataName + ": ");
                answer = sc.nextInt();
                correctInput = false;
            } catch (InputMismatchException a){
                sc.next();
                System.out.println("Please input an integer");
            }
        } while (correctInput);
        return answer;
    }

    public static void SearchHero(Connection con, String connectionString){
        ArrayList<Hero> heroes = new ArrayList<>();
        String search = " ";
        System.out.println("Select your command:\n" +
                "1 Search by ID\n" +
                "2 Search by Name\n" +
                "3 Return to Main Menu");
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

        heroes.forEach((hero) -> System.out.println(hero));
    }

    public static void ListAllHeroes(Connection con, String connectionString){
        ArrayList<Hero> heroes = new ArrayList<>();

        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");
            String requestedName = "";

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

        heroes.forEach((hero) -> System.out.println(hero));
    }
}
