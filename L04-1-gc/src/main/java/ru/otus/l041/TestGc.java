package ru.otus.l041;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.lang.management.ManagementFactory.getMemoryPoolMXBeans;

public class TestGc {
    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        for(GarbageCollectorMXBean mBean: ManagementFactory.getGarbageCollectorMXBeans()) {
            ((NotificationEmitter) mBean).addNotificationListener(gcHandler, null, null);
        }
        List<String> integerList = new ArrayList<>();
        Random random = new Random(Integer.MAX_VALUE);
        int i = 0;
        while (true) {
            integerList.add(new String(new char[0]));
            i++;
            if (i < 0) {
                i = 0;
            }
            if (i % 6_000_000 == 0) {
                integerList = integerList.subList(0, integerList.size() - 500_000);
            }
            if (i % 15_500_000 == 0) {
                integerList = integerList.subList(1_000_000, integerList.size() - 1);
            }
        }
    }

    private static NotificationListener gcHandler = (notification, handback) -> {
        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            GarbageCollectionNotificationInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            StringBuilder sb = new StringBuilder();
            sb.append("->>> ").append(gcInfo.getGcAction())
                    .append(" >>> ").append(gcInfo.getGcCause())
                    .append(" >>> ").append(gcInfo.getGcName())
                    .append(" >>> ").append(gcInfo.getGcInfo().getDuration()).append(" ms ");
            System.out.println(sb.toString());
        }
    };

}

