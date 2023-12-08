package Years.Y2015.Day_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2015" + java.io.File.separator + "Day_02"
            + java.io.File.separator + "input.txt";
    static ArrayList<Gift> input = new ArrayList<>();

    public static void main(String[] args) {
        readInput();
        solvePartOne();
        solvePartTwo();
    }

    public static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTLOC))) {
            String temp;
            while ((temp = br.readLine()) != null)
                input.add(parse(temp));
        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
        }
    }

    private static Gift parse(String in) {
        String[] sizes = in.split("x");
        return new Gift(Integer.parseInt(sizes[0]), Integer.parseInt(sizes[1]), Integer.parseInt(sizes[2]));
    }

    public static void solvePartOne() {
         int sum = 0;
        for (Gift g : input) {
            sum += g.sizeSmallestSide + g.surfaceArea;
        }
        System.out.println(sum);
    }

    public static void solvePartTwo() {
        int sum = 0;
        for (Gift g : input) {
            sum += g.bowLen + g.ribbonLen;
        }
        System.out.println(sum);
    }
}
