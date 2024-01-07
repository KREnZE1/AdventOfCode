package Years.Y2023.Day_09;

import java.util.ArrayList;

public class Dataset {
    ArrayList<int[]> numberCollection;

    int firstNum = 0, lastNum = 0;

    public Dataset(int[] baseVals) {
        numberCollection = new ArrayList<>();
        numberCollection.add(baseVals);
        extrapolate();
    }

    private void extrapolate() {
        do {
            int[] prevRow = numberCollection.get(numberCollection.size() - 1);
            int[] curr = new int[prevRow.length - 1];
            numberCollection.add(curr);
            for (int i = 0; i < prevRow.length - 1; i++) {
                curr[i] = prevRow[i + 1] - prevRow[i];
            }
        } while (!endFound());

        findFirst();
        findLast();
    }

    private boolean endFound() {
        int[] lastRow = numberCollection.get(numberCollection.size() - 1);
        for (int i : lastRow)
            if (i != 0)
                return false;
        return true;
    }

    private void findFirst() {
        for (int i = numberCollection.size() - 2; i>= 0; i--) firstNum = numberCollection.get(i)[0] - firstNum;
    }

    private void findLast() {
        for (int i = numberCollection.size() - 2; i >= 0; i--) lastNum += numberCollection.get(i)[numberCollection.get(i).length - 1];
    }
}
