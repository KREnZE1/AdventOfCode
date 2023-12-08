package Years.Y2023.Day_02;

import java.util.ArrayList;

public class Game {

    ArrayList<Draw> draws;
    int index;

    public Game(int pIndex) {
        draws = new ArrayList<>();
        index = pIndex;
    }

    public void add(Draw pDraw) {
        draws.add(pDraw);
    }
}
