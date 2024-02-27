package models;

import interfaces.EntityDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static database.RaceCalls.GetRaces;


@AllArgsConstructor
@Getter @Setter
public class Hero implements EntityDB {
    private int id;
    private String name;
    private int race;
    private int strength;
    private int agility;
    private int intelligence;
    private int wins;
    private int losses;

    public String shortDescription(){
        return EntityDB.randomWord() + name + " of " + GetRaces(race) + " tribe ";
    }

    @Override
    public String toString() {
        String raceString = "";

        raceString = GetRaces(race);

        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", race=" + raceString +
                ", strength=" + strength +
                ", agility=" + agility +
                ", intelligence=" + intelligence +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}
