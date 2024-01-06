package Years.Y2023.Day_08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static final String INPUTLOC = "Years" + java.io.File.separator + "Y2023" + java.io.File.separator + "Day_08"
            + java.io.File.separator + "input.txt";
    static ArrayList<String> input = new ArrayList<>();
    static String instruction;
    static Node begin;
    static ArrayList<Node> beginnings = new ArrayList<>(), endings = new ArrayList<>();

    public static void main(String[] args) {
        readInput();
        cleanInput();
        solvePartOne();
        solvePartTwo();
    }

    public static void readInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUTLOC))) {
            String temp;
            while ((temp = br.readLine()) != null)
                input.add(temp);
        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
        }
    }

    public static void cleanInput() {
        instruction = input.get(0);

        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 2; i < input.size(); i++) {
            nodes.add(new Node(input.get(i).split(" ")[0]));
        }
        //With the list of all nodes they can be connected individually based on their name

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).name.equals("AAA"))
                begin = nodes.get(i);

            if (nodes.get(i).name.charAt(2) == 'A')
                beginnings.add(nodes.get(i));

            String nameLeft = input.get(i + 2).substring(7, 10);
            String nameRight = input.get(i + 2).substring(12, 15);
            for (int j = 0; j < nodes.size(); j++) {
                if (nodes.get(j).name.equals(nameLeft))
                    nodes.get(i).setLeft(nodes.get(j));
                if (nodes.get(j).name.equals(nameRight))
                    nodes.get(i).setRight(nodes.get(j));
            }
        }
    }

    public static void solvePartOne() {
        Node curr = begin;
        int index = 0;
        int repetitions = 0;
        while (!curr.name.equals("ZZZ")) {
            curr = (instruction.charAt(index++) == 'L') ? curr.left : curr.right;
            if (index >= instruction.length()) {
                repetitions++;
                index = 0;
            }
        }
        System.out.println(repetitions * instruction.length() + index);
    }

    public static void solvePartTwo() {
        //TODO: Optimize
        ArrayList<Node> currentPositions = beginnings;
        int index = 0;
        int repetitions = 0;
        while (!allDone(currentPositions)) {
            for (int i=0; i<currentPositions.size(); i++) {
                Node n = currentPositions.remove(i);
                currentPositions.add(i, (instruction.charAt(index) == 'L') ? n.left : n.right);
            }
            System.out.println("Curr: " + repetitions * instruction.length() + index);
            if (++index >= instruction.length()) {
                repetitions++;
                index = 0;
            }
        }
        System.out.println(repetitions * instruction.length() + index);
    }

    private static boolean allDone(ArrayList<Node> curr) {
        for (Node n : curr) {
            if (n.name.charAt(2) != 'Z')
                return false;
        }
        return true;
    }
}
