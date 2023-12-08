package Years.Y2015.Day_03;

public class Coordinate {
    int x, y;

    public Coordinate(int pX, int pY) {
        this.x = pX;
        this.y = pY;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) return false;
        Coordinate newO = (Coordinate) o;
        if (x == newO.x && y == newO.y) return true;
        return false;
    }
}
