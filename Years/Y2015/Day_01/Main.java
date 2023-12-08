package Years.Y2015.Day_01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static final String INPUTLOC = "Years"+java.io.File.separator+"Y2015"+java.io.File.separator+"Day_01"+java.io.File.separator+"input.txt";
    static String input = "";

    public static void main(String[] args) {
        readInput();
        solvePartOne();
        solvePartTwo();
    }

    public static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTLOC))) {
            String temp;
            while ((temp = br.readLine()) != null) input = parse(temp);
        } catch(IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
        }
    }

    private static String parse(String in) {
        return in;
    }

    public static void solvePartOne() {
        int sum = 0;
        for (char c : input.toCharArray()) {
            if (c == '(') sum++;
            else if (c == ')') sum--;
        }
        System.out.println(sum);

    }

    public static void solvePartTwo() {
        int sum = 0;
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '(') sum++;
            else if (input.charAt(i) == ')') sum--;

            if (sum == -1) {
                System.out.println(i+1);
                break;
            }
        }
    }
}
