package Years.Y2023.Day_07;

public enum Handstrength {
    FIVEOFAKIND(6),
    FOUROFAKIND(5),
    FULLHOUSE(4),
    THREEOFAKIND(3),
    TWOPAIRS(2),
    ONEPAIR(1),
    NOTHING(0);

    int value;

    Handstrength(int pVal) {
        this.value = pVal;
    }
}
