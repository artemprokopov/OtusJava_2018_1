package ru.otus.l021;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.util.Objects;

public class Main {
    public static void main(String[] args)  {
        new Main().watchSizeMemoryObject(new Object());
    }

    private void watchSizeMemoryObject(Object object)  {
        if (Objects.isNull(object)) {
            return;
        }
        System.out.println(ObjectSizeCalculator.getObjectSize(object));
    }
}
