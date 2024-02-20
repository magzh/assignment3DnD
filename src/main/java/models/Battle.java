package models;

import interfaces.Combat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Battle implements Combat {
    private int id;
    private String name;
    private int fighter1;
    private int fighter2;
    private boolean winner;
    private String date;

    public void GenerateName(){

    }

    public String outcome(String f1, String f2){
        return "";
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
