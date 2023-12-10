package Years.Y2023.Day_05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2023" + java.io.File.separator + "Day_05"
            + java.io.File.separator + "input.txt";
    static ArrayList<String> input = new ArrayList<>();
    static ArrayList<BigInteger> seedNumbers = new ArrayList<>();
    static ArrayList<Converter> conversions = new ArrayList<>();

    public static void main(String[] args) {
        readInput();
        prepareInput();
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

    private static String parse(String in) {
        return in;
    }

    public static void prepareInput() {
        String[] seeds = input.get(0).split(": ")[1].split(" ");
        for (String s : seeds)
            seedNumbers.add(new BigInteger(s));

        for (int i = 2; i < input.size(); i++) {
            if (Character.isLetter(input.get(i).charAt(0))) {
                Converter c = new Converter();
                while (++i < input.size() && input.get(i).length() > 0) {
                    String[] vals = input.get(i).split(" ");
                    c.add(new Shifter(new BigInteger(vals[1]), new BigInteger(vals[2]), new BigInteger(vals[0])));
                }
                conversions.add(c);
            }
        }
    }

    public static void solvePartOne() {
        BigInteger min = null;
        BigInteger currVal = null;

        for (BigInteger seed : seedNumbers) {
            currVal = seed;
            for (Converter c : conversions)
                currVal = c.convert(currVal);
            if (min == null || currVal == min.min(currVal))
                min = currVal;
        }

        System.out.println(min);
    }

    public static void solvePartTwo() {
        //TODO Optimize (or use Linux instead of Windows because there it works :shrug:)
        BigInteger min = null;
        BigInteger currVal = null;

        for (int i = 0; i < seedNumbers.size(); i += 2) {
            System.out.println("Total: " + i + " out of " + seedNumbers.size());
            System.out.println("min = " + min);
            for (int j = 0; j < seedNumbers.get(i + 1).intValue(); j++) {
                currVal = seedNumbers.get(i).add(new BigInteger(Integer.toString(j)));
                // System.out.println("Current: " + currVal + ", "
                //         + currVal.subtract(seedNumbers.get(i)).divide(seedNumbers.get(i + 1)) + "%");
                for (Converter c : conversions)
                    currVal = c.convert(currVal);
                if (min == null || currVal == min.min(currVal))
                    min = currVal;
            }
        }

        System.out.println(min);
    }
}
