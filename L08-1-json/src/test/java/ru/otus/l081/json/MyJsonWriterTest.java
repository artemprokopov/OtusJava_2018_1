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
 *  .
 */
public class MyJsonWriterTest {
    /**
     *  .
     * @throws IllegalAccessException .
     * @throws FileNotFoundException .
     */
    @Test
    public void write() throws IllegalAccessException, FileNotFoundException {
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