package Utilities;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        System.out.println(new File("Utilities"+File.separatorChar+"Baseclass.txt").length());
        for (File dir : new File("Years"+File.separatorChar+"Y2023").listFiles()) {
            for (File f : dir.listFiles()) {
                if (aboutEqual(1110, f.length())) System.out.println(f.getParent());
            }
        }
    }

    private static boolean aboutEqual(long a, long b) {
        return (a-b)<5 && (a-b)>-5;
    }
}
