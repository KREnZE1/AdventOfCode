package Years.Y2023.Day_03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2023" + java.io.File.separator + "Day_03"
            + java.io.File.separator + "input.txt";
    static char[][] input = new char[140][];

    public static void main(String[] args) {
        readInput();
        solvePartOne();
        solvePartTwo();
    }

    public static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTLOC))) {
            String temp;
            int i = 0;
            while ((temp = br.readLine()) != null) {
                input[i++] = parse(temp);
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
        }
    }

    private static char[] parse(String in) {
        return in.toCharArray();
    }

    public static void solvePartOne() {
        //Warning: A number can be next to two different symbols and will be counted twice in this case, which it shouldn't
        int sum = 0;
        String num = "";
        int currJ = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (!Character.isDigit(input[i][j]) && input[i][j] != '.') {
                    //Check all fields with a radius of one and gather all numbers
                    if (i > 0) {
                        currJ = j;
                        while (currJ > 0 && Character.isDigit(input[i - 1][currJ - 1])) { //Checks to the top left of the symbol
                            num = input[i - 1][currJ - 1] + num;
                            currJ--;
                        }

                        if (Character.isDigit(input[i - 1][j]))
                            num += input[i - 1][j]; //Checks above the symbol
                        else if (!num.equals("")) {
                            sum += Integer.parseInt(num);
                            num = "";
                        }

                        currJ = j;
                        while (currJ < (input[i - 1].length - 1) && Character.isDigit(input[i - 1][currJ + 1])) { //Checks to the top right of the symbol
                            num += input[i - 1][currJ + 1];
                            currJ++;
                        }

                        if (!num.equals("")) {
                            sum += Integer.parseInt(num);
                            num = "";
                        }
                    }

                    currJ = j;
                    while (currJ > 0 && Character.isDigit(input[i][currJ - 1])) { //Checks to the left of the symbol
                        num = input[i][currJ - 1] + num;
                        currJ--;
                    }

                    if (!num.equals("")) {
                        sum += Integer.parseInt(num);
                        num = "";
                    }

                    currJ = j;
                    while (currJ < (input[i].length - 1) && Character.isDigit(input[i][currJ + 1])) { //Checks to the right of the symbol
                        num += input[i][currJ + 1];
                        currJ++;
                    }

                    if (!num.equals("")) {
                        sum += Integer.parseInt(num);
                        num = "";
                    }

                    if (i < (input.length - 1)) {
                        currJ = j;
                        while (currJ > 0 && Character.isDigit(input[i + 1][currJ - 1])) { //Checks to the bottom left of the symbol
                            num = input[i + 1][currJ - 1] + num;
                            currJ--;
                        }

                        if (Character.isDigit(input[i + 1][j]))
                            num += input[i + 1][j]; //Checks below the symbol
                        else if (!num.equals("")) {
                            sum += Integer.parseInt(num);
                            num = "";
                        }

                        currJ = j;
                        while (currJ < (input[i + 1].length - 1) && Character.isDigit(input[i + 1][currJ + 1])) { //Checks to the bttom right of the symbol
                            num += input[i + 1][currJ + 1];
                            currJ++;
                        }

                        if (!num.equals("")) {
                            sum += Integer.parseInt(num);
                            num = "";
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }

    public static void solvePartTwo() {
        int sum = 0;
        int currJ = 0;
        String num = "";
        int a = 0;
        int b = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                a=0; b=0;
                if (input[i][j] == '*') {
                    if (i > 0) {
                        currJ = j;
                        while (currJ > 0 && Character.isDigit(input[i - 1][currJ - 1])) { //Checks to the top left of the symbol
                            num = input[i - 1][currJ - 1] + num;
                            currJ--;
                        }

                        if (Character.isDigit(input[i - 1][j]))
                            num += input[i - 1][j]; //Checks above the symbol
                        else if (!num.equals("")) {
                            if (a == 0)
                                a = Integer.parseInt(num);
                            else if (b == 0)
                                b = Integer.parseInt(num);
                            else
                                continue;
                            num = "";
                        }

                        currJ = j;
                        while (currJ < (input[i - 1].length - 1) && Character.isDigit(input[i - 1][currJ + 1])) { //Checks to the top right of the symbol
                            num += input[i - 1][currJ + 1];
                            currJ++;
                        }

                        if (!num.equals("")) {
                            if (a == 0)
                                a = Integer.parseInt(num);
                            else if (b == 0)
                                b = Integer.parseInt(num);
                            else
                                continue;
                            num = "";
                        }
                    }

                    currJ = j;
                    while (currJ > 0 && Character.isDigit(input[i][currJ - 1])) { //Checks to the left of the symbol
                        num = input[i][currJ - 1] + num;
                        currJ--;
                    }

                    if (!num.equals("")) {
                        if (a == 0)
                            a = Integer.parseInt(num);
                        else if (b == 0)
                            b = Integer.parseInt(num);
                        else
                            continue;
                        num = "";
                    }

                    currJ = j;
                    while (currJ < (input[i].length - 1) && Character.isDigit(input[i][currJ + 1])) { //Checks to the right of the symbol
                        num += input[i][currJ + 1];
                        currJ++;
                    }

                    if (!num.equals("")) {
                        if (a == 0)
                            a = Integer.parseInt(num);
                        else if (b == 0)
                            b = Integer.parseInt(num);
                        else
                            continue;
                        num = "";
                    }

                    if (i < (input.length - 1)) {
                        currJ = j;
                        while (currJ > 0 && Character.isDigit(input[i + 1][currJ - 1])) { //Checks to the bottom left of the symbol
                            num = input[i + 1][currJ - 1] + num;
                            currJ--;
                        }

                        if (Character.isDigit(input[i + 1][j]))
                            num += input[i + 1][j]; //Checks below the symbol
                        else if (!num.equals("")) {
                            if (a == 0)
                                a = Integer.parseInt(num);
                            else if (b == 0)
                                b = Integer.parseInt(num);
                            else
                                continue;
                            num = "";
                        }

                        currJ = j;
                        while (currJ < (input[i + 1].length - 1) && Character.isDigit(input[i + 1][currJ + 1])) { //Checks to the bttom right of the symbol
                            num += input[i + 1][currJ + 1];
                            currJ++;
                        }

                        if (!num.equals("")) {
                            if (a == 0)
                                a = Integer.parseInt(num);
                            else if (b == 0)
                                b = Integer.parseInt(num);
                            else
                                continue;
                            num = "";
                        }
                    }

                    if (a != 0 && b != 0) sum += a*b;
                }
            }
        }

        System.out.println(sum);
    }
}
