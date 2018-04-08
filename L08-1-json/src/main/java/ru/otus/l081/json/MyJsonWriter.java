package ru.otus.l081.json;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Класс серилизиции объекта в JSON.
 * Используем библиотеку javax.json.
 * @author Artem Prokopov
 * @since 15/03/2018
 * @version 1.0
 */
public class MyJsonWriter implements IJsonWriter {
    /**
     *  Поле сохраняет путь куда серелизуем объекты.
     */
    private final File dirPath;

    /**
     *  Конструктор, по умолчанию задаем текущуюпапку.
     */
    public MyJsonWriter() {
        this.dirPath = new File(".//");
    }

    /**
     *  Конструктор с параметром, можем задать путь для лериктории для хранения серилизованных объектов.
     * @param initDirPath путь к дериктории хранения серилизованных объектов в JSON объектов.
     */
    public MyJsonWriter(File initDirPath) {
        if (initDirPath.isDirectory()) {
            this.dirPath = initDirPath;
        } else {
            throw new IllegalArgumentException("It is not a directory!!!");
        }
    }
    /**
     * Метод серилизует переданный объек в JSON.
     * @param writeObject серелизуемый объект.
     */
    @Override
    public void write(Object writeObject) {
        Class clazz = writeObject.getClass();
        String fileName = clazz.getSimpleName() + ".json";
        File file = new File(this.dirPath, fileName);
        JsonWriterFactory writerFactory = Json.createWriterFactory(null);
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            JsonWriter jsonWriter = writerFactory.createWriter(writer);
            jsonWriter.write(addJson(writeObject).build());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Создаем {@link JsonObjectBuilder} из переданного серелизуемого объекта.
     * @param <T> тип объекта.
     * @param writeObject серелизуемый объект.
     * @return .
     */
    private <T> JsonObjectBuilder addJson(T  writeObject) {
        Class clazzWrite = writeObject.getClass();
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder objectBuilder = factory.createObjectBuilder();
        Field[] field = clazzWrite.getDeclaredFields();
        for (Field fieldInspect : field) {
            fieldInspect.setAccessible(true);
            Object fieldObject = null;
            try {
                fieldObject = fieldInspect.get(writeObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fieldObject == null) {
                objectBuilder.addNull(fieldInspect.getName());
                continue;
            }
            if (fieldInspect.getType().equals(int.class)
                    || fieldInspect.getType().equals(Integer.class)) {
                objectBuilder.add(fieldInspect.getName(), (int) fieldObject);
                continue;
            }
            if (fieldInspect.getType().equals(long.class)
                    || fieldInspect.getType().equals(Long.class)) {
                objectBuilder.add(fieldInspect.getName(), (long) fieldObject);
                continue;
            }
            if (fieldInspect.getType().equals(double.class)
                    || fieldInspect.getType().equals(Double.class)) {
                objectBuilder.add(fieldInspect.getName(), (double) fieldObject);
                continue;
            }
            if (fieldInspect.getType().equals(float.class)
                    || fieldInspect.getType().equals(Float.class)) {
                objectBuilder.add(fieldInspect.getName(), (float) fieldObject);
                continue;
            }
            if (fieldInspect.getType().equals(boolean.class)
                    || fieldInspect.getType().equals(Boolean.class)) {
                objectBuilder.add(fieldInspect.getName(), (boolean) fieldObject);
                continue;
            }
            if (fieldInspect.getType().equals(String.class)) {
                objectBuilder.add(fieldInspect.getName(), (String) fieldObject);
                continue;
            }
            if (fieldInspect.getType().equals(BigInteger.class)) {
                objectBuilder.add(fieldInspect.getName(), (BigInteger) fieldObject);
                continue;
            }
            if (fieldInspect.getType().equals(BigDecimal.class)) {
                objectBuilder.add(fieldInspect.getName(), (BigDecimal) fieldObject);
                continue;
            }
            if (fieldInspect.getType().isArray()) {
                if (fieldInspect.getType().getComponentType().isPrimitive()
                        || fieldInspect.getType().getComponentType().equals(Integer.class)
                        || fieldInspect.getType().getComponentType().equals(Long.class)
                        || fieldInspect.getType().getComponentType().equals(Float.class)
                        || fieldInspect.getType().getComponentType().equals(Double.class)
                        || fieldInspect.getType().getComponentType().equals(Boolean.class)) {
                  objectBuilder.add(fieldInspect.getName(), addPrimitiveArray(fieldObject));
                  continue;
                }
                objectBuilder.add(fieldInspect.getName(), addObjectArrayBuilder((Object[]) fieldObject));
                continue;
            }
            objectBuilder.add(fieldInspect.getName(), addJson(fieldObject));
        }
        return objectBuilder;
    }

    /**
     * Метод добавляет элементы массива объекты, элементы массива не примитивного типа
     * и не объекты обертки {@link Integer}, {@link Long}, {@link Float}, {@link Double},
     * {@link Boolean}б в {@link JsonArrayBuilder}.
     * и возвращает его.
     * @param addArray массив элементы котрого добавляем в {@link JsonArrayBuilder}.
     * @return {@link JsonArrayBuilder} заполненый.
     */
    private JsonArrayBuilder addObjectArrayBuilder(Object[] addArray) {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonArrayBuilder arrayBuilder = factory.createArrayBuilder();
        Class<?> componentType = addArray.getClass().getComponentType();
        if (componentType.equals(String.class)) {
            for (String o : (String[]) addArray) {
                if (o == null) {
                    arrayBuilder.addNull();
                    continue;
                }
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        if (componentType.equals(BigInteger.class)) {
            for (BigInteger o : (BigInteger[]) addArray) {
                if (o == null) {
                    arrayBuilder.addNull();
                    continue;
                }
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        if (componentType.equals(BigDecimal.class)) {
            for (BigDecimal o : (BigDecimal[]) addArray) {
                if (o == null) {
                    arrayBuilder.addNull();
                    continue;
                }
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        for (Object o : addArray) {
            if (o == null) {
                arrayBuilder.addNull();
                continue;
            }
            arrayBuilder.add(addJson(o));
        }
        return arrayBuilder;
    }

    /**
     * Метод добавляет элементы массива если они примитивного типа, или объекты обертки
     * {@link Integer}, {@link Long}, {@link Float}, {@link Double},
     * {@link Boolean} в {@link JsonArrayBuilder}.
     * и возвращает его.
     * @param addArray массив элементы котрого добавляем в {@link JsonArrayBuilder}.
     * @return {@link JsonArrayBuilder} заполненый.
     */
    private JsonArrayBuilder addPrimitiveArray(Object addArray) {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonArrayBuilder arrayBuilder = factory.createArrayBuilder();
        Class<?> componentType = addArray.getClass().getComponentType();
        if (componentType.equals(int.class)) {
            for (int o : (int[]) addArray) {
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        if (componentType.equals(Integer.class)) {
            for (Integer o : (Integer[]) addArray) {
                if (o == null) {
                    arrayBuilder.addNull();
                    continue;
                }
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }

        if (componentType.equals(long.class)) {
            for (long o : (long[]) addArray) {
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        if (componentType.equals(Long.class)) {
            for (Long o : (Long[]) addArray) {
                if (o == null) {
                    arrayBuilder.addNull();
                    continue;
                }
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        if (componentType.equals(double.class)) {
            for (double o : (double[]) addArray) {
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        if (componentType.equals(Double.class)) {
            for (Double o : (Double[]) addArray) {
                if (o == null) {
                    arrayBuilder.addNull();
                    continue;
                }
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        if (componentType.equals(float.class)) {
            for (float o : (float[]) addArray) {
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }

        if (componentType.equals(Float.class)) {
            for (Float o : (Float[]) addArray) {
                if (o == null) {
                    arrayBuilder.addNull();
                    continue;
                }
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        if (componentType.equals(boolean.class)) {
            for (boolean o : (boolean[]) addArray) {
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        if (componentType.equals(Boolean.class)) {
            for (Boolean o : (Boolean[]) addArray) {
                if (o == null) {
                    arrayBuilder.addNull();
                    continue;
                }
                arrayBuilder.add(o);
            }
            return arrayBuilder;
        }
        return arrayBuilder;
    }
}
