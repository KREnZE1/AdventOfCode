package Years.Y2023.Day_05;

import java.util.ArrayList;
import java.math.BigInteger;

public class Converter {

    ArrayList<Shifter> shiftVals = new ArrayList<>();

    public Converter() {

    }

    public void add(Shifter s) {shiftVals.add(s);}

    public BigInteger convert(BigInteger in) {
        for (Shifter s : shiftVals) {
            if (s.contains(in)) return s.shift(in);
        }
        return in;
    }
}