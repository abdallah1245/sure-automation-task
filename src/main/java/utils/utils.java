package utils;

public class utils {
    public static String generateRandomStreet() {
        int randomNum = (int)(Math.random() * 10000);
        return "Street_" + randomNum;
    }
}
