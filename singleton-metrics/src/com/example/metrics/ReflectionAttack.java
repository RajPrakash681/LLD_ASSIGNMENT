package com.example.metrics;

import java.lang.reflect.Constructor;

/**
 * Attempts to create multiple instances via reflection.
 * Starter allows this. After fix, it must fail.
 */
public class ReflectionAttack {

    public static void main(String[] args) throws Exception {
        MetricsRegistry singleton = MetricsRegistry.getInstance();

        Constructor<MetricsRegistry> ctor = MetricsRegistry.class.getDeclaredConstructor();
        ctor.setAccessible(true);

        try {
            MetricsRegistry evil = ctor.newInstance();
            System.out.println("singleton: " + System.identityHashCode(singleton));
            System.out.println("evil:      " + System.identityHashCode(evil));
            System.out.println("same? " + (singleton == evil));
        } catch (Exception e) {
            System.out.println("reflection blocked: " + e.getCause().getMessage());
        }
    }
}
