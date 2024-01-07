package Years.Y2023.Day_10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2023" + java.io.File.separator + "Day_10"
            + java.io.File.separator + "input.txt";
    static ArrayList<char[]> input = new ArrayList<>();

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

    private static char[] parse(String in) {
        return in.toCharArray();
    }

    public static void solvePartOne() {
        //Step 1: Find start position
        Position start = new Position(0, 0);
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length; j++) {
                if (input.get(i)[j] == 'S') {
                    start.x = i;
                    start.y = j;
                }
            }
        }

        //Step 2: Traverse the maze
        int stepCounter = 0;
        Position currPos = start;
        Position last = currPos;
        do {
            //Can move up?
                //Is there a space
                //Is the space connected (pointing towards current space)
            //Was I just on the upper space
                //Check: New position = last Position?
                // -> Yes: Dont
                // -> No: last = curr, curr = new
            //Assuming possible and not last -> move there
                //Increase the step counter
            //Assuming impossible try right
            //Assuming that impossible too try down
            //Assuming that impossible too try left
            //Assuming that impossible send error message and exit

            for (Direction d : Direction.values()) {
                Position suggestedNew = calcNew(d, currPos);
                if (movePossible(d, suggestedNew) && !suggestedNew.equals(last)) {
                    last = currPos;
                    currPos = suggestedNew;
                    stepCounter++;
                }
            }
        } while(!currPos.equals(start));

        //Step 3: Calculate distance
        System.out.println(stepCounter/2);
    }

    private static boolean movePossible(Direction d, Position p) {
        //TODO: Check if this does what it is expected to
        return switch (d) {
            case UP -> ((p.y>0) && (input.get(p.y)[p.x] == '7' || input.get(p.y)[p.x] == '|' || input.get(p.y)[p.x] == 'F'));
            case RIGHT -> ((p.x<input.get(p.y).length-1) && (input.get(p.y)[p.x] == '7' || input.get(p.y)[p.x] == '-' || input.get(p.y)[p.x] == 'J'));
            case DOWN -> ((p.y<input.size()-1) && (input.get(p.y)[p.x] == 'L' || input.get(p.y)[p.x] == '|' || input.get(p.y)[p.x] == 'J'));
            case LEFT -> ((p.x>0) && (input.get(p.y)[p.x] == 'L' || input.get(p.y)[p.x] == '-' || input.get(p.y)[p.x] == 'F'));
        };
    }

    private static Position calcNew(Direction d, Position curr) {
        //TODO: Check if this does what it is expected to
        return switch (d) {
            case UP -> new Position(curr.x, curr.y-1);
            case RIGHT -> new Position(curr.x+1, curr.y);
            case DOWN -> new Position(curr.x, curr.y+1);
            case LEFT -> new Position(curr.x-1, curr.y);
        };
    }

    public static void solvePartTwo() {
        //TODO: Implement
    }
}
