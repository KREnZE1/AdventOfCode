package Years.Y2023.Day_08;

public class Node {
    Node right, left;
    String name;

    public Node(String pName) {
        this.name = pName;
    }

    public void setRight(Node pRight) {
        this.right = pRight;
    }
    public void setLeft(Node pLeft) {
        this.left = pLeft;
    }
}
