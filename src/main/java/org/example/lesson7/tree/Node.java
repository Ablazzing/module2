package org.example.lesson7.tree;

public class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    // Несбалансированное дерево
    //     5
    //    / \
    //   3  10
    //  / \
    // 1   4
    //  \
    //   2


    // Преобразование в красно-черное дерево
    //
    //      4
    //     / \
    //    3   5
    //   /     \
    //  2       10
    // /
    //1
    //       5
    //      / \
    //     4  10
    //    /
    //   3
    //  /
    // 1

    public void add(int newValue) {
        if (newValue > value) {
            if (this.right == null) {
                this.right = new Node(newValue);
            } else {
                if (this.right.value > newValue) {
                    if (this.right.left ==  null) {
                        this.right.left = new Node(newValue);
                    } else if (this.right.value < newValue) {
                        this.right.add(newValue);
                    }
                } else if (this.right.value < newValue) {
                    this.right.add(newValue);
                }
            }

        } else if (newValue < value) {
            if (this.left == null) {
                this.left = new Node(newValue);
            } else {
                if (this.left.value < newValue) {
                    if (this.left.right == null) {
                        this.left.right = new Node(newValue);
                    } else if ( this.left.value > newValue) {
                        this.left.add(newValue);
                    }
                } else if (this.left.value > newValue) {
                    this.left.add(newValue);
                }
            }
        }
    }

    public void addLeft(int newValue) {

    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }


}
