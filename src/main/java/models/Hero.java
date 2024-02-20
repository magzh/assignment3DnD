package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Hero {
    public int idd;
    private int id;
    private String name;
    private int race;
    private int strength;
    private int agility;
    private int intelligence;
    private int wins;
    private int losses;

    @Override
    public String toString() {
        String raceString = "";
        switch (this.race){
            case 0:
                raceString = "Human";
                break;
            case 1:
                raceString = "Dwarf";
                break;
            case 2:
                raceString = "Elf";
                break;
            default:
        }

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
