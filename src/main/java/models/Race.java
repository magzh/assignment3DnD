package models;

import interfaces.EntityDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Race implements EntityDB {
    private int id;
    private String name;

    public String shortDescription(){
        return name;
    }
}
