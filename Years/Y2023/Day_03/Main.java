package Years.Y2023.Day_03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static final String INPUTLOC = "Years"+java.io.File.separator+"Y2023"+java.io.File.separator+"Day_03"+java.io.File.separator+"input.txt";
    static char[][] input = new char[140][];

    public static void main(String[] args) {
        readInput();
        solvePartOne();
        solvePartTwo();
    }

    public static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTLOC))) {
            String temp;
            int i=0;
            while ((temp = br.readLine()) != null) {
                input[i++] = parse(temp);
            }
        } catch(IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
        }
    }

    private static char[] parse(String in) {
        return in.toCharArray();
    }

    public static void solvePartOne() {
        //TODO: Implement
    }

    public static void solvePartTwo() {
        //TODO: Implement
    }
}
