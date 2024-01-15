package Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Function;

public class Remover {

    private static boolean delHappened = false;

    public static void main(String[] args) {
        //TODO: Add new methods
        if (args.length == 0)
            printHelpAndExit();
        switch (args[0]) {
            case "All" -> remAll(null);
            case "Empty" -> remAllEmpty(null);
            default -> printHelpAndExit();
        }
    }

    public static void printHelpAndExit() {
        System.out.println("How to use this file:");
        System.out.println(" - Use param 'All' to remove every file");
        System.out.println(" - Use param 'Empty' to remove all files without any content");
        System.out.println("CAUTION: THESE ACTIONS ARE IRREVERSABLE!!!");
        System.exit(1);
    }

    public static void remAll(String regex) {
        ArrayList<Function<File, Boolean>> conditions = new ArrayList<>();
        if (regex != null) conditions.add((f) -> f.getName().contains(regex));
        remFiles(new File("Years"), conditions);
    }

    public static void remAllEmpty(String regex) {
        ArrayList<Function<File, Boolean>> conditions = new ArrayList<>();
        conditions.add((f) -> f.getName().endsWith("java") ? isUnedited(f.length()) : f.length() == 0);
        if (regex!= null) conditions.add((f) -> f.getName().contains(regex));
        remFiles(new File("Years"), conditions);
    }

    public static void remYear(String year) {
        ArrayList<Function<File, Boolean>> conditions = new ArrayList<>();
        conditions.add((f) -> true);
        File delDir = new File("Years"+File.separatorChar+"Y"+year);
        if (delDir.exists()) remFiles(delDir, conditions);
    }

    public static void remYears(String beginYear, String endYear) {
        int begin = Integer.parseInt(beginYear);
        int end = Integer.parseInt(endYear);
        for (int i = begin; i <= end; i++) {
            remYear(Integer.toString(i));
        }
    }

    private static void remFiles(File baseDir, ArrayList<Function<File, Boolean>> conditions) {
        do {
            delHappened = false;
            for (File f : baseDir.listFiles())
                recRem(f, conditions);
        } while (delHappened);
        if (baseDir.isFile() || (baseDir.isDirectory() && baseDir.list().length == 0)) baseDir.delete();
    }

    private static void recRem(File f, ArrayList<Function<File, Boolean>> conditions) {
        if (f.isFile()) {
            for (Function<File, Boolean> condition : conditions) {
                if (!condition.apply(f))
                    return;
            }
            f.delete();
            delHappened = true;
        } else if (f.isDirectory() && f.list().length == 0) {
            f.delete();
            delHappened = true;
        }
         else {
            for (File _f : f.listFiles())
                recRem(_f, conditions);
        }
    }

    private static boolean isUnedited(long a) {
        long b = new File("Utilities"+File.separatorChar+"Baseclass.txt").length();
        int allowedDiff = 2;
        return (a-b)<allowedDiff && (a-b)>-allowedDiff;
    }
}
