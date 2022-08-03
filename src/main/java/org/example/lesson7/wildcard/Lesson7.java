package org.example.lesson7.wildcard;


import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class Lesson7 {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(new Object());

        ArrayList<Cat> cats2 = new ArrayList<>();


        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(new Cat());

        ArrayList<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());

        copy(cats, animals);
        System.out.println(animals);

        //ArrayList<Cat> != ArrayList<Animal>

        // object
        // animal
        // cat

        Storage storage = new Storage(dogs);
        List<? extends Animal> data = storage.getData();
        data.stream().forEach(e -> {
            try {
                System.out.println(e);
                Constructor<? extends Animal> constructor = e.getClass().getDeclaredConstructor();
                constructor.setAccessible(true);
                Animal cat = constructor.newInstance();
                System.out.println(cat);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


    }

    public static void copy(List<? extends Animal> src, List<? super Animal> dst) {
        src.stream().forEach(e -> dst.add(e));
    }
}
