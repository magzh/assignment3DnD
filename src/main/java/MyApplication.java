import database.CombatCalls;
import database.HeroCalls;
import database.RaceCalls;
import models.Race;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://mouse.db.elephantsql.com:5432/cdwxnizx?user=cdwxnizx&password=bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3&sslmode=require";

        Connection con = null;

        ArrayList<Race> races = RaceCalls.RaceList(con, connectionString);

        boolean running = true;
        while (running){
            System.out.println("Select your command:\n" +
                    "1 Create a Hero\n" +
                    "2 Search a Hero\n" +
                    "3 List all Heroes\n" +
                    "4 Battle Mode\n" +
                    "5 Exit");
            Scanner sc = new Scanner(System.in);
            int mode = sc.nextInt();
            switch (mode){
                case 1:
                    // create
                    HeroCalls.CreateHero(con, connectionString);
                    break;
                case 2:
                    // search
                    HeroCalls.SearchHero(con, connectionString);
                    break;
                case 3:
                    // list
                    HeroCalls.ListAllHeroes(con, connectionString);
                    break;
                case 4:
                    // battle
                    CombatCalls.BattleMode(con,connectionString);
                    break;
                default:
                    running = false;
            }
        }
    }
}
