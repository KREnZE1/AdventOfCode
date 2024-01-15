package Years.Y2023.Day_10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    private enum Direction {
        UP {
            @Override
            Direction invert() {
                return Direction.DOWN;
            }
        },
        RIGHT {
            @Override
            Direction invert() {
                return Direction.LEFT;
            }
        },
        DOWN {
            @Override
            Direction invert() {
                return Direction.UP;
            }
        },
        LEFT {
            @Override
            Direction invert() {
                return Direction.RIGHT;
            }
        };

        abstract Direction invert();
    }

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2023" + java.io.File.separator + "Day_10"
            + java.io.File.separator + "input.txt";
    static ArrayList<char[]> input = new ArrayList<>();

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

    private static char[] parse(String in) {
        return in.toCharArray();
    }

    public static void solvePartOne() {
        readInput();

        //Step 1: Find start location
        Position start = new Position(0, 0);
        boolean done = false;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length; j++) {
                start = new Position(j, i);
                if (getChar(start) == 'S') {
                    done = true;
                    break;
                }
            }
            if (done)
                break;
        }

        //Step 2: Replace S with correct character
        HashSet<Direction> connections = new HashSet<>(2);
        for (Direction d : Direction.values()) {
            Position newPos = calcNew(d, start);
            if (newPos == null)
                continue;
            if (points(d.invert(), newPos))
                connections.add(d);
        }
        input.get(start.y)[start.x] = getReplacement(connections);

        //Step 3: Traverse the maze
        int stepCounter = 0;
        Position currPos = start;
        Position lastPos = currPos;
        do {
            for (Direction d : Direction.values()) {
                Position newPos = calcNew(d, currPos);
                if (points(d, currPos) && !lastPos.equals(newPos)) {
                    stepCounter++;
                    lastPos = currPos;
                    currPos = newPos;
                    break;
                }
            }
        } while (!currPos.equals(start));

        //Step 4: Calculate solution
        System.out.println(stepCounter / 2);
    }

    private static char getChar(Position p) {
        return input.get(p.y)[p.x];
    }

    private static Position calcNew(Direction d, Position curr) {
        return switch (d) {
            case UP -> ((curr.y > 0) ? new Position(curr.x, curr.y - 1) : null);
            case RIGHT -> ((curr.x < input.get(curr.y).length - 1) ? new Position(curr.x + 1, curr.y) : null);
            case DOWN -> ((curr.y < input.size() - 1) ? new Position(curr.x, curr.y + 1) : null);
            case LEFT -> ((curr.x > 0) ? new Position(curr.x - 1, curr.y) : null);
        };
    }

    private static char getReplacement(HashSet<Direction> connections) {
        if (connections.contains(Direction.UP) && connections.contains(Direction.RIGHT))
            return 'L';
        else if (connections.contains(Direction.UP) && connections.contains(Direction.LEFT))
            return 'J';
        else if (connections.contains(Direction.UP) && connections.contains(Direction.DOWN))
            return '|';
        else if (connections.contains(Direction.RIGHT) && connections.contains(Direction.DOWN))
            return 'F';
        else if (connections.contains(Direction.RIGHT) && connections.contains(Direction.LEFT))
            return '-';
        else if (connections.contains(Direction.DOWN) && connections.contains(Direction.LEFT))
            return '7';
        else
            return '.';
    }

    private static boolean points(Direction d, Position p) {
        char c = getChar(p);
        return switch (d) {
            case UP -> (c == 'J' || c == '|' || c == 'L');
            case RIGHT -> (c == 'F' || c == '-' || c == 'L');
            case DOWN -> (c == '7' || c == '|' || c == 'F');
            case LEFT -> (c == '7' || c == '-' || c == 'J');
        };
    }

    public static void solvePartTwo() {
        readInput();
        //TODO: Implement
    }
}