package Years.Y2023.Day_05;

import java.math.BigInteger;

public class Shifter {

    BigInteger begin, range, shiftBy;

    public Shifter(BigInteger pBegin, BigInteger pRange, BigInteger dest) {
        this.begin = pBegin;
        this.range = pRange;
        this.shiftBy = dest.subtract(begin);
    }

    public boolean contains(BigInteger in) {
        return (in.compareTo(begin) >= 0 && in.compareTo(begin.add(range)) < 0);
    }

    public BigInteger shift(BigInteger in) {
        return in.add(shiftBy);
    }
}
