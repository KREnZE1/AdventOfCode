package Utilities;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateStructure {

    public static void main(String[] args) {
        if (args.length < 2)
            printHelpAndExit();
        switch (args[0]) {
            case "Year":
                createYearStructure(args[1]);
                break;

            default:
                break;
        }
    }

    public static void printHelpAndExit() {

    }

    public static void createYearStructure(String yearNum) {
        final String basePath = "Years" + File.separator + "Y" + yearNum + File.separator;

        for (int i = 1; i < 26; i++) {
            String childName = convertToDayName(i);
            File newDir = new File(basePath, childName);
            if (newDir.exists()) {
                System.err.println("Error with path " + basePath + " and dir name " + childName);
                return;
            }
            newDir.mkdir();
            createInput(newDir.getPath());
            createTestInput(newDir.getPath());
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
            file.createNewFile();
        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
            System.exit(-1);
        }
        return file;
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
                BufferedReader br = new BufferedReader(new FileReader("Utilities" + File.separator + "Example.txt"))) {

            String temp;
            for (int i=0; i<42; i++) {
                temp = br.readLine();
                if (i == 0) {
                    String begin = temp.substring(0, 15); //Should contain everything up to the Y in the year num
                    String year = curr.getPath().substring(7, 11); //Should contain the year number
                    String mid = temp.substring(19, 24); //Should contain .Day_
                    String day = curr.getPath().substring(16, 18); //Should contain the day number
                    bw.write(begin+year+mid+day+";\n");
                } else if (i == 9) {
                    String begin = temp.substring(0, 68);
                    String year = curr.getPath().substring(7, 11); //Should contain the year number
                    String mid = temp.substring(72, 102);
                    String day = curr.getPath().substring(16, 18); //Should contain the day number
                    String end = temp.substring(104);
                    bw.write(begin+year+mid+day+end+"\n");
                } else bw.write(temp+"\n");
            }

        } catch (IOException ioe) {
            System.err.println(ioe.getLocalizedMessage());
            System.err.println(ioe.getStackTrace());
            System.exit(-1);
        }
    }
}