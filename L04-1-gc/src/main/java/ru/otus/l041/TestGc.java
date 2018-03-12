package ru.otus.l041;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
/**
 * Класс тестирование различных GC.
 * Параметры запуска -Xms196m -Xmx196m если памяти меньше, то GC ConcMarkSweepGC падает
 * с OutOfMemoryError очень быстро.
 * Далее устанавливаем параметры для выполнения класса с соответствующим GC:
 * -XX:+UseConcMarkSweepGC
 * -XX:+UseSerialGC
 * -XX:+UseParallelGC
 * -XX:+UseG1GC
 * Далее наблюдаем сборки мусора и время потраченное на работу GC.
 * @author Artem Prokopov
 * @since 08/03/2018
 * @version 1.0
 */

public class TestGc {
    /**
     * Хранилище количества сборок GC того или иного типа.
     */
    private static HashMap<String, Integer> resultGC = new HashMap<>();

    /**
     * Вход в приложение с подтиканием по памяти.
     * @param args передаваемые в приложение аргументы, здесь ни как не влияют.
     */
    public static void main(String[] args) {
        List<GarbageCollectorMXBean> gcMxBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean mBean: gcMxBeans) {
            ((NotificationEmitter) mBean).addNotificationListener(gcHandler, null, null);
        }
        System.out.println(gcMxBeans.get(0).getObjectName());
        List<String> integerList = new ArrayList<>();
        Random random = new Random(Integer.MAX_VALUE);
        int i = 0;
        while (true) {
            integerList.add(new String(new char[0]));
            i++;
            if (i < 0) {
                i = 0;
            }
            if (i % 3_000_000 == 0) {
                integerList = integerList.subList(0, integerList.size() - 500_000);
            }
            if (i % 5_500_000 == 0) {
                integerList = integerList.subList(500_000, integerList.size() - 1);
            }
        }
    }

    /**
     * Создаем слушателя уведомлений.
     * Выводит в консоль режимы работы GC, время сборки и количество сборок того или иного типа,
     * прошедших с начала работы приложения.
     */
    private static NotificationListener gcHandler = (notification, handback) -> {
        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            GarbageCollectionNotificationInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            String gcName = gcInfo.getGcName();
            if (resultGC.containsKey(gcName)) {
                int i = resultGC.get(gcName);
                i++;
                resultGC.put(gcName, i);
            } else {
                resultGC.put(gcName, 1);
            }
            StringBuilder sb = new StringBuilder().append("->>> ")
                    .append(gcName)
                    .append(" >>> ")
                    .append(gcInfo.getGcAction())
                    .append(" >>> ")
                    .append(gcInfo.getGcInfo().getDuration()).append(" ms ");
            System.out.println(sb.toString());
        }
        for (String s: resultGC.keySet()) {
            System.out.print(s + " ");
            System.out.println(resultGC.get(s));
        }
    };
}

