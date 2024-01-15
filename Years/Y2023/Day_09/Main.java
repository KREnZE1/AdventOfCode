package Years.Y2023.Day_09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2023" + java.io.File.separator + "Day_09"
            + java.io.File.separator + "input.txt";
    static ArrayList<Dataset> input = new ArrayList<>();

    public static void main(String[] args) {
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

    private static Dataset parse(String in) {
        String[] numbers = in.split(" ");
        int[] actualValues = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            actualValues[i] = Integer.parseInt(numbers[i]);
        return new Dataset(actualValues);
    }

    public static void solvePartOne() {
        readInput();

        int sum = 0;
        for (Dataset d : input)
            sum += d.lastNum;
        System.out.println(sum);
    }

    public static void solvePartTwo() {
        readInput();
        
        int sum = 0;
        for (Dataset d : input)
            sum += d.firstNum;
        System.out.println(sum);
    }
}
