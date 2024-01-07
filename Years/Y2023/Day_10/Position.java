package Years.Y2023.Day_10;

public class Position {
    int x, y;

    public Position(int pX, int pY) {
        this.x = pX;
        this.y = pY;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position checkAgainst = (Position) o;
        return this.x == checkAgainst.x && this.y == checkAgainst.y;
    }

    public void right() {this.x++;}
    public void left() {this.x--;}
    public void down() {this.y--;}
    public void up() {this.y++;}
}
