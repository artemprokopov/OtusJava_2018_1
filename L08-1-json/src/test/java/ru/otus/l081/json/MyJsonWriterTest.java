package ru.otus.l081.json;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import static org.junit.Assert.assertEquals;

/**
 * Тест для {@link MyJsonWriter}.
 * @author Artem Prokopov
 * @since 07/04/2018
 * @version 1.0
 */
public class MyJsonWriterTest {
    /**
     *  Тестируем метод {@link MyJsonWriter#write(Object)}.
     *  Содаем объект {@link TestJsonObject}, серелизуем его в JSON,
     *  далее десерилизуем его с помощью фреймворка gson, и сравниваем объекты
     *  до серилизации в JSON и после десерелизации.
     * @throws FileNotFoundException исключение если файл не найден.
     */
    @Test
    public void write() throws  FileNotFoundException {
        File file = new File(System.getProperty("user.dir"));
        IJsonWriter myJsonWriter = new MyJsonWriter(file);
        TestJsonObject testJsonObject = new TestJsonObject();
        myJsonWriter.write(testJsonObject);
        Reader reader = new BufferedReader(new FileReader(file.getPath() + "//"
                + testJsonObject.getClass().getSimpleName() + ".json"));
        Gson gson = new Gson();
        TestJsonObject testJsonObject1 = gson.fromJson(reader, TestJsonObject.class);
        assertEquals(testJsonObject1, testJsonObject);
    }
}