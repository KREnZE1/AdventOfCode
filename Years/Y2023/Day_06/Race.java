package Years.Y2023.Day_06;

import java.math.BigInteger;

public class Race {

    BigInteger time, distance, minTime, value;

    public Race(String pTime, String pDist) {
        this.time = new BigInteger(pTime);
        this.distance = new BigInteger(pDist);
        this.minTime = findMinTime();
        if (this.minTime.equals(BigInteger.ZERO))
            throw new IllegalArgumentException();
        this.value = (this.time.subtract(this.minTime)).subtract(this.minTime).add(BigInteger.ONE);
    }

    @Override
    public String toString() {
        return "[" + time + "; " + distance + "]";
    }

    public BigInteger findMinTime() {
        for (BigInteger i = BigInteger.ONE; i.compareTo(this.time) < 0; i = i.add(BigInteger.ONE))
            if (this.time.subtract(i).multiply(i).compareTo(this.distance) > 0)
                return i;
        return BigInteger.ZERO;
    }

}
