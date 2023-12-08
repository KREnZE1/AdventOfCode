package Years.Y2015.Day_03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2015" + java.io.File.separator + "Day_03"
            + java.io.File.separator + "input.txt";
    static String input = "";
    static ArrayList<Coordinate> visitedHouses = new ArrayList<>();
    static int x = 0, y = 0;
    static int subX = 0, subY = 0;

    public static void main(String[] args) {
        readInput();
        solvePartOne();
        solvePartTwo();
    }

    public static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTLOC))) {
            String temp;
            while ((temp = br.readLine()) != null)
                input = parse(temp);
        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
        }
    }

    private static String parse(String in) {
        return in;
    }

    public static void solvePartOne() {
        visitedHouses.add(new Coordinate(x, y));
        for (char c : input.toCharArray()) {
            if (c == '<')
                x--;
            else if (c == '>')
                x++;
            else if (c == '^')
                y++;
            else if (c == 'v')
                y--;
            Coordinate curr = new Coordinate(x, y);
            if (!visitedHouses.contains(curr))
                visitedHouses.add(curr);
        }
        System.out.println(visitedHouses.size());
    }

    public static void solvePartTwo() {
        visitedHouses = new ArrayList<>();
        x = 0;
        y = 0;
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                if (input.charAt(i) == '<')
                    x--;
                else if (input.charAt(i) == '>')
                    x++;
                else if (input.charAt(i) == '^')
                    y++;
                else if (input.charAt(i) == 'v')
                    y--;
            } else {
                if (input.charAt(i) == '<')
                    subX--;
                else if (input.charAt(i) == '>')
                    subX++;
                else if (input.charAt(i) == '^')
                    subY++;
                else if (input.charAt(i) == 'v')
                    subY--;
            }
            Coordinate curr = (i % 2 == 0 ? new Coordinate(x, y) : new Coordinate(subX, subY));
            if (!visitedHouses.contains(curr))
                visitedHouses.add(curr);
        }
        System.out.println(visitedHouses.size());
    }
}
//Dont ask me, why this isn't more beautiful or more cleanly written, Java screws around and doesn't work as I want with complete objects, so I use the ugly version