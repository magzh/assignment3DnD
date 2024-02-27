package interfaces;

import java.util.Random;

public interface EntityDB {
    static String randomWord(){
        Random rand = new Random();
        return switch (rand.nextInt(5)) {
            case 0 -> "Brave ";
            case 1 -> "Strong ";
            case 2 -> "Lucky ";
            case 3 -> "Holy ";
            default -> "";
        };
    }
    String shortDescription();
}
