package Years.Y2018.Day_03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years"+java.io.File.separator+"Y2018"+java.io.File.separator+"Day_03"+java.io.File.separator+"input.txt";
    static ArrayList<String> input = new ArrayList<>();

    public static void main(String[] args) {
        solvePartOne();
        solvePartTwo();
    }

    public static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTLOC))) {
            String temp;
            while ((temp = br.readLine()) != null) input.add(parse(temp));
        } catch(IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
        }
    }

    private static String parse(String in) {
        //TODO: Implement
        return in;
    }

    public static void solvePartOne() {
        readInput();
        //TODO: Implement
    }

    public static void solvePartTwo() {
        readInput();
        //TODO: Implement
    }
}
