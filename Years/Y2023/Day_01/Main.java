package Years.Y2023.Day_01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years"+java.io.File.separator+"Y2023"+java.io.File.separator+"Day_01"+java.io.File.separator+"input.txt";
    static ArrayList<String> input = new ArrayList<>();

    static char writtenNum = ' ';

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
        return in;
    }

    public static void solvePartOne() {
        readInput();

        int sum = 0;
        char firstNum = ' ';
        char lastNum = ' ';
        boolean firstFound = false;

        for (int i = 0; i < input.size(); i++) {
            String currString = input.get(i);
            for (int j = 0; j < currString.length(); j++) {
                if (Character.isDigit(currString.charAt(j))) {
                    if (firstFound)
                        lastNum = currString.charAt(j);
                    else {
                        firstFound = true;
                        firstNum = lastNum = currString.charAt(j);
                    }
                }
            }
            sum += Integer.parseInt("" + firstNum + lastNum);
            firstFound = false;
        }
        System.out.println(sum);
    }

    public static void solvePartTwo() {
        readInput();
        
        int sum = 0;
        boolean firstFound = false;
        char firstNum = ' ';
        char lastNum = ' ';

        for (int i = 0; i < input.size(); i++) {
            String currString = input.get(i);
            for (int j = 0; j < currString.length(); j++) {
                if (Character.isDigit(currString.charAt(j))) {
                    if (firstFound)
                        lastNum = currString.charAt(j);
                    else {
                        firstFound = true;
                        firstNum = lastNum = currString.charAt(j);
                    }
                } else if (Character.isLetter(currString.charAt(j))) {
                    if (containAndSetWrittenNumber(currString.substring(j))) {
                        if (firstFound)
                            lastNum = writtenNum;
                        else {
                            firstFound = true;
                            firstNum = lastNum = writtenNum;
                        }
                    }
                }
            }
            sum += Integer.parseInt("" + firstNum + lastNum);
            firstFound = false;
        }

        System.out.println(sum);
    }

    private static boolean containAndSetWrittenNumber(String in) {
        writtenNum = ' ';
        if (in.length()<=2) return false;
        else if (in.length() >= 3 && in.charAt(0) == 'o' && in.charAt(1) == 'n' && in.charAt(2) == 'e') writtenNum = '1';
        else if (in.length() >= 3 && in.charAt(0) == 't' && in.charAt(1) == 'w' && in.charAt(2) == 'o') writtenNum = '2';
        else if (in.length() >= 5 && in.charAt(0) == 't' && in.charAt(1) == 'h' && in.charAt(2) == 'r' && in.charAt(3) == 'e' && in.charAt(4) == 'e') writtenNum = '3';
        else if (in.length() >= 4 && in.charAt(0) == 'f' && in.charAt(1) == 'o' && in.charAt(2) == 'u' && in.charAt(3) == 'r') writtenNum = '4';
        else if (in.length() >= 4 && in.charAt(0) == 'f' && in.charAt(1) == 'i' && in.charAt(2) == 'v' && in.charAt(3) == 'e') writtenNum = '5';
        else if (in.length() >= 3 && in.charAt(0) == 's' && in.charAt(1) == 'i' && in.charAt(2) == 'x') writtenNum = '6';
        else if (in.length() >= 5 && in.charAt(0) == 's' && in.charAt(1) == 'e' && in.charAt(2) == 'v' && in.charAt(3) == 'e' && in.charAt(4) == 'n') writtenNum = '7';
        else if (in.length() >= 5 && in.charAt(0) == 'e' && in.charAt(1) == 'i' && in.charAt(2) == 'g' && in.charAt(3) == 'h' && in.charAt(4) == 't') writtenNum = '8';
        else if (in.length() >= 4 && in.charAt(0) == 'n' && in.charAt(1) == 'i' && in.charAt(2) == 'n' && in.charAt(3) == 'e') writtenNum = '9';
        return writtenNum != ' ';
    }
}
