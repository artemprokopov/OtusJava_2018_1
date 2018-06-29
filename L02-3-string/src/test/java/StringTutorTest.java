//CHECKSTYLE:OFF
import org.junit.Test;

import static org.junit.Assert.*;

public class StringTutorTest {
    /**
     * Замените все null в assertEquals на true или false
     */
    @Test
    public void testStringEquals() {
        String s1 = "aaa";
        String s2 = "aaa";
        s1.equals(s2);
        String s3 = new String("aaa");
        StringTutor.log("адрес объекта s1: "+System.identityHashCode(s1));
        StringTutor.log("адрес объекта s2: "+System.identityHashCode(s2));
        assertTrue(s1 == s2);
        assertTrue(s1.equals(s2));
        StringTutor.log("адрес объекта s3: "+System.identityHashCode(s3));
        assertFalse(s1 == s3);
        // метод intern() позволяет получить строку из пула строк
        String s4 = s3.intern();
        StringTutor.log("адрес объекта s4: "+System.identityHashCode(s4));
        assertTrue(s1 == s4);
        // тестируем пересоздание объекта каждый раз при изменении
        s3 = s3+"bbb";
        StringTutor.log("адрес измененного объекта s3: "+System.identityHashCode(s3));
        s3 = s3.substring(0, 3); // s3 снова "aaa"
        assertFalse(s3 == s1);
        assertTrue(s3.equals(s1));
        assertTrue(s3.intern() == s1);
    }

    @Test
    public void testCheckGreeting() {
        StringTutor stringTutor = new StringTutor();
        assertTrue(stringTutor.checkGreeting("Привет, Иван Иванов!"));
        assertTrue(stringTutor.checkGreeting("Привет,Петр Первый!"));
        assertTrue(stringTutor.checkGreeting("Привет, Петр Первый!"));
        assertTrue(stringTutor.checkGreeting("Привет, Петр Первый !"));

        assertFalse("В начале должно быть слово Привет и запятая",
                stringTutor.checkGreeting("Здравствуйте, Петр Первый!"));
        assertFalse("В конце должен быть восклицательный знак",
                stringTutor.checkGreeting("Привет, Петр Первый"));
        assertFalse("Имя слишком короткое",
                stringTutor.checkGreeting("Привет, Ли Сунь!"));
        assertFalse("Фамилия слишком короткая",
                stringTutor.checkGreeting("Привет, Куй Ли!"));
        assertFalse("Должны быть указаны и имя, и фамилия",
                stringTutor.checkGreeting("Привет, Петр!"));
        assertFalse("Первая буква имени должна быть заглавной",
                stringTutor.checkGreeting("Привет, петр Первый!"));
        assertFalse("Первая буква фамилии должна быть заглавной",
                stringTutor.checkGreeting("Привет, Петр первый!"));
    }
}
//CHECKSTYLE:ON
