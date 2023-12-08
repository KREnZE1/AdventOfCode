package Years.Y2023.Day_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years"+java.io.File.separator+"Y2023"+java.io.File.separator+"Day_02"+java.io.File.separator+"input.txt";
    static ArrayList<Game> input = new ArrayList<>();

    public static void main(String[] args) {
        readInput();
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

    private static Game parse(String in) {
        String begin = in.split(": ")[0];
        int index = Integer.parseInt(begin.substring(5));
        String[] draws = in.split(": ")[1].split("; ");
        Game out = new Game(index);
        for (int i = 0; i < draws.length; i++) {
            String[] draw = draws[i].split(", ");
            int red = 0;
            int green = 0;
            int blue = 0;
            for (int j = 0; j < draw.length; j++) {
                if (draw[j].contains("red"))
                    red = Integer.parseInt(draw[j].split(" ")[0]);
                else if (draw[j].contains("green"))
                    green = Integer.parseInt(draw[j].split(" ")[0]);
                else if (draw[j].contains("blue"))
                    blue = Integer.parseInt(draw[j].split(" ")[0]);
                else
                    System.err.println("Draw contains no suitable color: " + draw[j]);
            }
            out.add(new Draw(red, green, blue));
        }
        return out;
    }

    public static void solvePartOne() {
        final int RED = 12;
        final int GREEN = 13;
        final int BLUE = 14;
        int sum = 0;

        for (int i = 0; i < input.size(); i++) {
            boolean impossibleDraw = false;
            ArrayList<Draw> curr = input.get(i).draws;
            for (int j = 0; j < curr.size(); j++) {
                if (curr.get(j).red > RED || curr.get(j).green > GREEN || curr.get(j).blue > BLUE)
                    impossibleDraw = true;
            }
            if (impossibleDraw)
                continue;
            sum += input.get(i).index;
        }

        System.out.println(sum);
    }

    public static void solvePartTwo() {
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            int minRed = Integer.MIN_VALUE;
            int minGreen = Integer.MIN_VALUE;
            int minBlue = Integer.MIN_VALUE;
            ArrayList<Draw> curr = input.get(i).draws;
            for (int j = 0; j < curr.size(); j++) {
                if (curr.get(j).red > minRed)
                    minRed = curr.get(j).red;
                if (curr.get(j).green > minGreen)
                    minGreen = curr.get(j).green;
                if (curr.get(j).blue > minBlue)
                    minBlue = curr.get(j).blue;
            }
            sum += (minRed * minGreen * minBlue);
        }

        System.out.println(sum);
    }
}
