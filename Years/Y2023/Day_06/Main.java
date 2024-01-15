package Years.Y2023.Day_06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2023" + java.io.File.separator + "Day_06"
            + java.io.File.separator + "input.txt";
    static ArrayList<String> input = new ArrayList<>();

    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();
    }

    public static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTLOC))) {
            String temp;
            while ((temp = br.readLine()) != null)
                input.add(temp);
        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
        }
    }

    private static Race[] parseRaces(boolean moreThanOneRace) {
        String timesWithWhitespace = input.get(0).split(":")[1];
        String cleanTimes = moreThanOneRace ? cleanForMany(timesWithWhitespace) : cleanForOne(timesWithWhitespace);
        String distWithWhitespace = input.get(1).split(":")[1];
        String cleanDist = moreThanOneRace ? cleanForMany(distWithWhitespace) : cleanForOne(distWithWhitespace);

        Race[] races = new Race[cleanTimes.split(" ").length];
        for (int i = 0; i < races.length; i++) {
            races[i] = new Race(cleanTimes.split(" ")[i], cleanDist.split(" ")[i]);
        }
        return races;
    }

    private static String cleanForMany(String in) {
        String out = "";
        boolean flag = false;
        for (char c : in.toCharArray()) {
            if (out.equals("")) {
                if (c == ' ')
                    continue;
                else if (Character.isDigit(c))
                    out += c;
            } else {
                if (c == ' ' && !flag) {
                    flag = true;
                    out += " ";
                } else if (Character.isDigit(c)) {
                    out += c;
                    flag = false;
                }
            }
        }
        return out;
    }

    private static String cleanForOne(String in) {
        String out = "";
        for (char c : in.toCharArray()) if (Character.isDigit(c)) out += c;
        return out;
    }

    public static void solvePartOne() {
        readInput();

        BigInteger product = BigInteger.ONE;
        Race[] races = parseRaces(true);
        for (int i = 0; i < races.length; i++) product = product.multiply(races[i].value);
        System.out.println(product);
    }

    public static void solvePartTwo() {
        readInput();
        
        Race[] races = parseRaces(false); // races should only contain one element here
        if (races.length > 1) throw new IllegalArgumentException("More than one element in an array which only considers the first Element");
        System.out.println(races[0].value);
    }

}
