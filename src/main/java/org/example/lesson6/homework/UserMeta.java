package org.example.lesson6.homework;

@GetMetaData
public class UserMeta {
    @GetMetaData
    private String name;
    private int count;

    public UserMeta(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @GetMetaData
    public void getSuperName(String prefix) {
        System.out.println(prefix);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMetaData
    public void getSomething(Integer a) {
        System.out.println(a);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
