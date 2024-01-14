package Utilities;

import java.io.File;
import java.util.function.Function;

public class Remover {

    private static boolean delHappened = false;

    public static void main(String[] args) {
        //TODO: Add new methods
        if (args.length == 0)
            printHelpAndExit();
        switch (args[0]) {
            case "All" -> delAll();
            case "Empty" -> delAllEmpty();
            default -> printHelpAndExit();
        }
    }

    public static void printHelpAndExit() {
        //TODO: Add new methods
        System.out.println("How to use this file:");
        System.out.println(" - Use param 'All' to delete every file");
        System.out.println(" - Use param 'Empty' to delete all files without any content");
        System.out.println("CAUTION: THESE ACTIONS ARE IRREVERSABLE!!!");
        System.exit(1);
    }

    public static void delAll() {
        delFiles((f) -> true);
    }

    public static void delAllEmpty() {
        delFiles((f) -> f.length() == 0);
    }

    public static void delInput() {
        delFiles((f) -> f.getName() == "input.txt");
    }

    public static void delInputEmpty() {
        delFiles((f) -> f.getName() == "input.txt" && f.length() == 0);
    }

    public static void delTestIn() {
        delFiles((f) -> f.getName() == "testInput.txt");
    }

    public static void delTestInEmpty() {
        delFiles((f) -> f.getName() == "testInput.txt" && f.length() == 0);
    }

    public static void delMain() {
        delFiles((f) -> f.getName() == "Main.java");
    }

    public static void delMainEmpty() {
        delFiles((f) -> f.getName() == "Main.java" && isUnedited(f.length()));
    }

    private static void delFiles(Function<File, Boolean> condition) {
        File baseDir = new File("Years");
        do {
            delHappened = false;
            for (File f : baseDir.listFiles())
                recDel(f, condition);
        } while (delHappened);
    }

    private static void recDel(File f, Function<File, Boolean> condition) {
        if (f.isFile() || (f.isDirectory() && f.list().length == 0)) {
            if (condition.apply(f)) {
                f.delete();
                delHappened = true;
            }
        } else {
            for (File _f : f.listFiles())
                recDel(_f, condition);
        }
    }

    private static boolean isUnedited(long a) {
        long b = new File("Utilities"+File.separatorChar+"Basetext.txt").length();
        int allowedDiff = 2;
        return (a-b)<allowedDiff && (a-b)>-allowedDiff;
    }
}
