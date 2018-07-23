package ru.otus.mycache;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Класс сохраняемого элемента кэша.
 * @param <K> тип ключа элемента, реализует интерфейс {@link KeyCache}.
 * @param <V> тип хранимого значения.
 * @author Artem Prokopov
 * @since 14/07/2018
 * @version 1.0
 */
public class CacheElementImpl<K, V> implements CacheElement<K, V> {

    /**
     * Софт ссылка на объет ключа.
     */
    private KeyCache<K> key;
    /**
     * Сохраняемое значени.
     */
    private final V value;
    /**
     * Время жизни элемена между обращениями.
     */
    private boolean notIdle = false;

    /**
     * Таймер запускающий проверки времени жизни элемента, и времени простоя, следующего
     * обращения к элементу.
     */
    private final Timer timer = new Timer(true);;


    /**
     * Конструктор.
     * @param initIdleTime время жизни элемента между обращениями.
     * @param initLifeTime время жизни элемента.
     * @param key ключ элемента.
     * @param value значение элемента.
     */
    public CacheElementImpl(K key, V value, long initIdleTime, long initLifeTime) {
        if (initIdleTime > 0) {
            timer.schedule(getIdleTimerTask(), 0, initIdleTime);
        }
        if (initLifeTime > 0) {
            timer.schedule(getLifeTimerTask(), 0, initLifeTime);
        }

        this.key = new KeyCacheImpl<>(key);
        this.value = value;
    }

    @Override
    public KeyCache<K> getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        this.notIdle = true;
        return this.value;
    }

    /**
     * Метод обнуляет ключ.
     */
    public void disposeKey() {
        timer.cancel();
        this.key = null;
    }

    /**
     * Задача для таймера отслеживающее время жизни элемента в зависимости от заданного максимального
     * времени между запросами элемента из кэша.
     * @return задача для таймера {@link CacheElementImpl#timer}
     */
    private TimerTask getIdleTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                if (!notIdle) {
                    disposeKey();
                }
            }
        };
    }
    /**
     * Задача для таймера отслеживающее время жизни элемента в зависимости от заданного максимального
     * времени между запросами элемента из кэша.
     * @return задача для таймера {@link CacheElementImpl#timer}
     */
    private TimerTask getLifeTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                disposeKey();
            }
        };
    }

}
