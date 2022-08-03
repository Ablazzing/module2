package org.example.lesson7.wildcard;

public class Cat extends Animal {
    private int countLives;

    public Cat() {
    }

    private Cat(Integer count) {
        this.countLives = count;
    }

    @Override
    public void makeSound() {
        System.out.println("Кот мяукает");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "countLives=" + countLives +
                '}';
    }
}
