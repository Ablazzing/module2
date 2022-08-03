package org.example.lesson7.wildcard;

import java.util.List;

public class Storage {
    List<? extends Animal> data;

    public Storage(List<? extends Animal> data) {
        this.data = data;
    }

    public List<? extends Animal> getData() {
        return data;
    }
}
