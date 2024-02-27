package models;

import interfaces.EntityDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Battle implements EntityDB {
    private int id;
    private String name;
    private int fighter1;
    private int fighter2;
    private boolean winner;
    private String date;

    public String shortDescription(){
        return "has scored a " + EntityDB.randomWord() + " victory against ";
    }


    @Override
    public String toString() {
        return "Battle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fighter1=" + fighter1 +
                ", fighter2=" + fighter2 +
                ", winner=" + winner +
                ", date='" + date + '\'' +
                '}';
    }
}
