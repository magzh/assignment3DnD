package database;

import models.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseCalls {


    public static void ListTableCall(Connection con, String connectionString, String tableName, ArrayList<String> columns){
        ArrayList<Object> list = new ArrayList<>();
        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");

            String sql2 = "SELECT ";
            for (int i = 0; i < columns.size(); i++) {
                sql2 += columns.get(i);
                if (columns.size() != i + 1){
                    sql2 += ", ";
                }
            }
            sql2 += " FROM " + tableName;

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql2);

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
                list.add(hero);
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
        list.forEach((row) -> System.out.println(row));
    }


    public static void InsertIntoCall(Connection con, String connectionString, String tableName, ArrayList<String> columns, ArrayList<String> values){
        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");

            String sql2 = "INSERT INTO " + tableName + " (";
            for (int i = 0; i < columns.size(); i++) {
                sql2 += columns.get(i);
                if (columns.size() != i + 1){
                    sql2 += ", ";
                }
            }
            sql2 += ")\n";
            sql2 += "VALUES('";
            for (int i = 0; i < values.size(); i++) {
                sql2 += values.get(i);
                if (columns.size() != i + 1){
                    sql2 += "', '";
                }
            }
            sql2 += "');";

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

    public static void RemoveCall(Connection con, String connectionString, String tableName, int id){
        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");

            String sql2 = "DELETE FROM " + tableName + "\n" + "WHERE id = " + id + ";";

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

    public static String InputStringData (String dataName, Scanner sc){
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

    public static ArrayList<Hero> ListTableSend(Connection con, String connectionString, String tableName, ArrayList<String> columns){
        ArrayList<Hero> list = new ArrayList<>();
        try {
            con = DriverManager.getConnection(connectionString,"cdwxnizx","bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3");

            String sql2 = "SELECT ";
            for (int i = 0; i < columns.size(); i++) {
                sql2 += columns.get(i);
                if (columns.size() != i + 1){
                    sql2 += ", ";
                }
            }
            sql2 += " FROM " + tableName;

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql2);

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
                list.add(hero);
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
        return list;
    }

    public static int InputIntData (String dataName, Scanner sc){
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
}
