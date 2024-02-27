import database.CombatCalls;
import database.HeroCalls;
import database.RaceCalls;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static database.AuthenticationCall.PasswordCheck;

public class MyApplication {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://mouse.db.elephantsql.com:5432/cdwxnizx?user=cdwxnizx&password=bi0XwY7hGdr4e7u_lWpQh_8G45RxB2A3&sslmode=require";

        Connection con = null;

        RaceCalls.RaceList(con, connectionString);

        boolean running = true;
        while (running){
            System.out.println("Select your command:\n" +
                    "1 Create a Hero\n" +
                    "2 Search a Hero\n" +
                    "3 List all Heroes\n" +
                    "4 Battle Mode\n" +
                    "5 Lore Master Mode\n" +
                    "6 Exit");
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
                case 5:
                    // battle
                    System.out.println("Enter Lore Password:");
                    String pw = sc.next();
                    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(pw);
                    if (m.find()){
                        System.out.println("Do not use special characters");
                    }else if (PasswordCheck(con, connectionString, pw)){
                        System.out.println("Select your lore command:\n" +
                                "1 Delete a Hero\n" +
                                "2 Delete a Battle\n" +
                                "3 Create a Race\n" +
                                "4 Delete a Race\n" +
                                "5 Exit");
                        mode = sc.nextInt();
                        switch (mode){
                            case 1:
                                // create
                                HeroCalls.DeleteHero(con, connectionString);
                                break;
                            case 2:
                                // list
                                CombatCalls.DeleteBattle(con, connectionString);
                                break;
                            case 3:
                                // battle
                                RaceCalls.CreateRace(con,connectionString);
                                break;
                            case 4:
                                // battle
                                RaceCalls.DeleteRace(con,connectionString);
                                break;
                            default:
                                running = false;
                        }
                    }else{
                        System.out.println("Wrong password");
                    }
                    break;
                default:
                    running = false;
            }
        }
    }
}
