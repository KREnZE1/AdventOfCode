package Years.Y2023.Day_07;

import java.util.HashMap;

public class Play {
    String cards;
    int bet;
    Handstrength best;
    HashMap<Character, Integer> occurrences;

    public Play(String pHand, int pBet) {
        this.cards = pHand;
        this.bet = pBet;
        index();
    }

    private void index() {
        occurrences = new HashMap<>();
        for (int i = 0; i < cards.length(); i++) {
            if (occurrences.containsKey(cards.charAt(i)))
                continue;
            occurrences.put(cards.charAt(i), 1);
            for (int j = i + 1; j < cards.length(); j++) {
                if (cards.charAt(i) == cards.charAt(j))
                    occurrences.replace(cards.charAt(i), occurrences.get(cards.charAt(i)) + 1);
            }
        }
    }

    public void evaluateHand() {
        best = Handstrength.NOTHING;
        testFive();
        if (best == Handstrength.NOTHING)
            testFour();
        if (best == Handstrength.NOTHING)
            testFull();
        if (best == Handstrength.NOTHING)
            testThree();
        if (best == Handstrength.NOTHING)
            testPairs();
    }

    private void testFive() {
        for (Character k : occurrences.keySet()) {
            if (occurrences.get(k) == 5)
                best = Handstrength.FIVEOFAKIND;
        }
    }

    private void testFour() {
        for (Character k : occurrences.keySet()) {
            if (occurrences.get(k) == 4)
                best = Handstrength.FOUROFAKIND;
        }
    }

    private void testFull() {
        boolean foundTwo = false;
        boolean foundThree = false;
        for (Character k : occurrences.keySet()) {
            if (occurrences.get(k) == 2)
                foundTwo = true;
            else if (occurrences.get(k) == 3)
                foundThree = true;
        }
        if (foundTwo && foundThree)
            best = Handstrength.FULLHOUSE;
    }

    private void testThree() {
        for (Character k : occurrences.keySet()) {
            if (occurrences.get(k) == 3)
                best = Handstrength.THREEOFAKIND;
        }
    }

    private void testPairs() {
        int amountPairs = 0;
        for (Character k : occurrences.keySet()) {
            if (occurrences.get(k) == 2)
                amountPairs++;
        }
        if (amountPairs == 2)
            best = Handstrength.TWOPAIRS;
        else if (amountPairs == 1)
            best = Handstrength.ONEPAIR;
    }

    public void evaluateWithJoker() {
        int numJokers = occurrences.getOrDefault('J', 0);
        if (numJokers == 0) {
            evaluateHand();
            return;
        }

        if (occurrences.size() <= 2) best = Handstrength.FIVEOFAKIND;
        else if (occurrences.size() == 3) {
            //Full House only if both non-Jokers are there twice, else four of a kind
            best = Handstrength.FOUROFAKIND;
            boolean first = false;
            for (Character k : occurrences.keySet())
                if (k != 'J' && occurrences.get(k) == 2) {
                    if (!first) first = true;
                    else best = Handstrength.FULLHOUSE;
                }



        }
        else if (occurrences.size() == 4) best = Handstrength.THREEOFAKIND;
        else best = Handstrength.ONEPAIR;
    }
}
