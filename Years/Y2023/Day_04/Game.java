package Years.Y2023.Day_04;

import java.util.ArrayList;

public class Game {

    ArrayList<Integer> winner, numbers;
    int amountOfThisCard;

    public Game(ArrayList<Integer> pWinner, ArrayList<Integer> pNumbers) {
        this.winner = pWinner;
        this.numbers = pNumbers;
        this.amountOfThisCard = 1;
    }

    public int calcPoints() {
        return (int)(Math.pow(2, countMatches())/2);
    }

    public int countMatches() {
        int matches = 0;
        for (Integer i : numbers) if (winner.contains(i)) matches++;
        return matches;
    }

    public void addCards(int amount) {this.amountOfThisCard += amount;}
}
