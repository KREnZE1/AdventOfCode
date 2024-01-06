package Years.Y2023.Day_07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2023" + java.io.File.separator + "Day_07"
            + java.io.File.separator + "input.txt";
    static ArrayList<Play> input = new ArrayList<>();
    static final char[] labels = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    static final char[] jokerLabels = {'J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'};
    static char[] compArray;

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

    private static Play parse(String in) {
        String[] inputs = in.split(" ");
        return new Play(inputs[0], Integer.parseInt(inputs[1]));
    }

    private static void bubblesort() {
        //It's Bubblesorting time
        boolean changeHappened = false;
        do {
            changeHappened = false;
            for (int i=1; i<input.size(); i++) {
                if (input.get(i-1) == getMoreValuablePlay(input.get(i), input.get(i-1))) {
                    Play temp = input.remove(i-1);
                    input.add(i, temp);
                    changeHappened = true;
                }
            }
        } while (changeHappened);
    }

    private static Play getMoreValuablePlay(Play a, Play b) {
        if (a.best != b.best) {
            return (a.best.value > b.best.value) ? a : b;
        }
        for (int i = 0; i < a.cards.length(); i++) {
            int firstIndex = 0;
            int secondIndex = 0;
            for (int j=0; j<compArray.length; j++) {
                if (a.cards.charAt(i) == compArray[j]) firstIndex = j;
                if (b.cards.charAt(i) == compArray[j]) secondIndex = j;
            }
            if (firstIndex != secondIndex) return (firstIndex > secondIndex ? a : b);
        }
        System.err.println("Hands are identical");
        return a;
    }

    private static int calcWinnings() {
        int sum = 0;
        for (int i=0; i<input.size(); i++) {
            sum += (input.get(i).bet * (i+1));
        }
        return sum;
    }

    public static void solvePartOne() {
        for (Play p : input) p.evaluateHand();
        compArray = labels;
        bubblesort();
        System.out.println(calcWinnings());
    }


    public static void solvePartTwo() {
        for (Play p : input) p.evaluateWithJoker();
        compArray = jokerLabels;
        bubblesort();
        System.out.println(calcWinnings());
    }
}
