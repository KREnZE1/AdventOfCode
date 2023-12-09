package Years.Y2023.Day_04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2023" + java.io.File.separator + "Day_04"
            + java.io.File.separator + "input.txt";
    static ArrayList<Game> input = new ArrayList<>();

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

    private static Game parse(String in) {
        ArrayList<Integer> winners = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();

        String[] winningNumbers = in.split(": ")[1].split(" \\| ")[0].split(" ");
        String[] drawnNumbers = in.split(": ")[1].split(" \\| ")[1].split(" ");

        for (String s : winningNumbers) {
            if (s.length() > 0)
                winners.add(Integer.parseInt(s));
        }

        for (String s : drawnNumbers) {
            if (s.length() > 0)
                numbers.add(Integer.parseInt(s));
        }

        return new Game(winners, numbers);
    }

    public static void solvePartOne() {
        int sum = 0;
        for (Game g : input)
            sum += g.calcPoints();
        System.out.println(sum);
    }

    public static void solvePartTwo() {
        for (int i = 0; i < input.size(); i++) {
            int matches = input.get(i).countMatches();
            for (int j = 1; j <= matches; j++) {
                if ((i+j)<input.size())
                input.get(i+j).addCards(input.get(i).amountOfThisCard);
            }
        }

        int sum = 0;
        for (Game g : input) sum += g.amountOfThisCard;
        System.out.println(sum);
    }
}
