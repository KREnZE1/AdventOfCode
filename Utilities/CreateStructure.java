package Utilities;

import java.io.File;

public class CreateStructure {


    public static void main(String[] args) {
        if (args.length<2) printHelpAndExit();
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
        final String basePath = "Years"+File.separator+"Y"+yearNum+File.separator;

        for (int i=1; i<26; i++) {
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
        if (daynum<10) output += "0"+Integer.toString(daynum);
        else output += Integer.toString(daynum);
        return output;
    }

    private static void createInput(String parentPath) {

    }

    private static void createTestInput(String parentPath) {

    }

    private static void createJavaClass(String parentPath) {
        
    }
}