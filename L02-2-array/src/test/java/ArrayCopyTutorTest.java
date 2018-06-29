//CHECKSTYLE:OFF
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayCopyTutorTest {
    private ArrayCopyTutor animals = new ArrayCopyTutor();
    private ArrayCopyTutor animalsTest = new ArrayCopyTutor();
    {
        animalsTest.addAnimal("Лошадь");
        animalsTest.addAnimal("Носорог");
        animalsTest.addAnimal("Собака");
        animalsTest.addAnimal("Змея");
        animalsTest.addAnimal("Обезьяна");
        animalsTest.addAnimal("Индюк");
        animalsTest.addAnimal("Косуля");
        animalsTest.addAnimal("Лев");
        animalsTest.addAnimal("Тигр");
        animalsTest.addAnimal("Кошка");
        animalsTest.addAnimal("Черепаха");
    }
    private String[] resultArrayString = {"Лошадь", "Носорог", "Собака",
            "Змея", "Обезьяна", "Индюк",
            "Косуля", "Лев", "Тигр",
            "Кошка", "Черепаха"};
    private String[] resultArrayStringInsert = {"Лошадь", "Человек", "Носорог", "Собака",
            "Змея", "Обезьяна", "Индюк",
            "Косуля", "Лев", "Тигр",
            "Кошка", "Черепаха"};
    private String[] resultArrayStringDelete0 = { "Носорог", "Собака",
            "Змея", "Обезьяна", "Индюк",
            "Косуля", "Лев", "Тигр",
            "Кошка", "Черепаха"};
    private String[] resultArrayStringDelete2 = {"Лошадь", "Носорог",
            "Змея", "Обезьяна", "Индюк",
            "Косуля", "Лев", "Тигр",
            "Кошка", "Черепаха"};
    private String[] resultArrayStringDeleteLast = {"Лошадь", "Носорог", "Собака",
            "Змея", "Обезьяна", "Индюк",
            "Косуля", "Лев", "Тигр",
            "Кошка"};

    @Test
    public void testAddAnimals() {
        animals.addAnimal("Лошадь");
        animals.addAnimal("Носорог");
        animals.addAnimal("Собака");
        animals.addAnimal("Змея");
        animals.addAnimal("Обезьяна");
        animals.addAnimal("Индюк");
        animals.addAnimal("Косуля");
        animals.addAnimal("Лев");
        animals.addAnimal("Тигр");
        animals.addAnimal("Кошка");
        animals.addAnimal("Черепаха");
        assertArrayEquals(resultArrayString, animals.toArray());
//        //deleteAnimal(2);

    }

    @Test
    public void testInsertAnimals() {
        animalsTest.insertAnimal(1, "Человек");
        assertArrayEquals(resultArrayStringInsert, animalsTest.toArray());
    }

    @Test
    public void testDelet0Animals() {
        animalsTest.deleteAnimal(0);
        assertArrayEquals(resultArrayStringDelete0, animalsTest.toArray());
    }

    @Test
    public void testDelete2Animals() {
        animalsTest.deleteAnimal(2);
        assertArrayEquals(resultArrayStringDelete2, animalsTest.toArray());
    }

    @Test
    public void testDeleteLastAnimals() {
        animalsTest.deleteAnimal(10);
        assertArrayEquals(resultArrayStringDeleteLast, animalsTest.toArray());
    }
}
//CHECKSTYLE:ON