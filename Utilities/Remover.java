package Utilities;

import java.io.File;
import java.util.function.Function;

public class Remover {

    private static boolean delHappened = false;

    public static void main(String[] args) {
        if (args.length == 0) printHelpAndExit();
        switch (args[0]) {
            case "All" -> delAll();
            case "Empty" -> delEmpty();
            default -> printHelpAndExit();
        }
    }

    public static void printHelpAndExit() {
        System.out.println("How to use this file:");
        System.out.println(" - Use param 'All' to delete every file");
        System.out.println(" - Use param 'Empty' to delete all files without any content");
        System.out.println("CAUTION: THESE ACTIONS ARE IRREVERSABLE!!!");
        System.exit(1);
    }

    public static void delAll() {
        Function<File, Boolean> isDeletable = a -> true;
        delFiles(isDeletable);
    }

    public static void delEmpty() {
        Function<File, Boolean> isDeletable = a -> a.length() == 0;
        delFiles(isDeletable);
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
        }
        else {
            for (File _f : f.listFiles())
                recDel(_f, condition);
        }
    }
}
