package ru.otus.l051;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Аннотация, для аннотирования методов выполняющихся до запуска каждого тестового метода.
 * @author Artem Prokopov
 * @since 15/03/2018
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBefore {
}
