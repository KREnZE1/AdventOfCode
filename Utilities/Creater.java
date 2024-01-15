package Utilities;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Creater {

    public static void main(String[] args) {
        if (args.length == 0)
            printHelpAndExit();
        switch (args[0]) {
            case "Years" -> loopThroughYears(args[1], args[2]);
            case "Year" -> createYearStructure(args[1]);
            default -> printHelpAndExit();
        }
    }

    public static void printHelpAndExit() {
        System.out.println("How to use this file:");
        System.out.println(" - Use param 'Year' to specify a single year for which to create the day structure");
        System.out.println(
                " - Use param 'Years' to specify two years. The day structure is created for both of those years and every year inbetween them");
        System.exit(1);
    }

    public static void loopThroughYears(String beginYear, String endYear) {
        int begin = Integer.parseInt(beginYear);
        int end = Integer.parseInt(endYear);
        for (int i = begin; i <= end; i++) {
            createYearStructure(Integer.toString(i));
        }
    }

    public static void createYearStructure(String yearNum) {
        final String basePath = "Years" + File.separator + "Y" + yearNum;
        File baseDir = new File(basePath);
        createDir(baseDir);
        for (int i = 1; i < 26; i++) {
            String childName = convertToDayName(i);
            File newDir = new File(basePath, childName);
            createDir(newDir);
            HashSet<String> alreadyExists = new HashSet<>();
            for (String s : newDir.list())
                alreadyExists.add(s);

            if (!alreadyExists.contains("input.txt"))
                createInput(newDir.getPath());
            if (!alreadyExists.contains("testInput.txt"))
                createTestInput(newDir.getPath());
            if (!alreadyExists.contains("Main.java"))
                createJavaClass(newDir.getPath());
        }
    }

    private static String convertToDayName(int daynum) {
        String output = "Day_";
        if (daynum < 10)
            output += "0" + Integer.toString(daynum);
        else
            output += Integer.toString(daynum);
        return output;
    }

    private static File createFile(String parentPath, String childName) {
        File file = new File(parentPath, childName);
        try {
            if (!file.exists()) {
                System.out.println("File with name " + file.getPath() + " doesn't yet exist");
                System.out.println("Creating new file with that name\n");
                file.createNewFile();
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
        }
        return file;
    }

    private static void createDir(File f) {
        if (!f.exists()) {
            System.out.println("Directory with name " + f.getPath() + " doesn't yet exist");
            System.out.println("Continuing with file creation inside newly created directory\n");
            f.mkdirs();
        }
    }

    private static void createInput(String parentPath) {
        createFile(parentPath, "input.txt");
        //TODO: Automatically fetch input content from the website?
        //Would require logging in, but could then basically save the newly opened website as a text file, because the entire content is relevant
    }

    private static void createTestInput(String parentPath) {
        createFile(parentPath, "testInput.txt");
        //TODO: Automatically fetch testInput content from the website?
        //Would not require logging in because the test cases are the same for every participant, but would require a lot of parsing because it is not uniformly positioned
    }

    private static void createJavaClass(String parentPath) {
        File curr = createFile(parentPath, "Main.java");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(curr));
                BufferedReader br = new BufferedReader(
                        new FileReader("Utilities" + File.separator + "Baseclass.txt"))) {

            String temp;
            for (int i = 0; i < 42; i++) {
                temp = br.readLine();
                if (i == 0) {
                    String begin = temp.substring(0, 15); //Should contain everything up to the Y in the year num
                    String year = curr.getPath().substring(7, 11); //Should contain the year number
                    String mid = temp.substring(19, 24); //Should contain .Day_
                    String day = curr.getPath().substring(16, 18); //Should contain the day number
                    bw.write(begin + year + mid + day + ";\n");
                } else if (i == 9) {
                    String begin = temp.substring(0, 68);
                    String year = curr.getPath().substring(7, 11); //Should contain the year number
                    String mid = temp.substring(72, 102);
                    String day = curr.getPath().substring(16, 18); //Should contain the day number
                    String end = temp.substring(104);
                    bw.write(begin + year + mid + day + end + "\n");
                } else
                    bw.write(temp + "\n");
            }

        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
        }
    }
}