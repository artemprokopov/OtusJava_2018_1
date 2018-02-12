package ru.otus.l021;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class Main<T> {
    public static void main(String[] args) throws  IOException {
        new Main<Integer[]>().watchSizeMemoryObject(new Integer[0]);
    }

    private void watchSizeMemoryObject(T object) throws IOException {
        if (Objects.isNull(object)) {
            return;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
        }
        System.out.println(baos.size());

    }

}
