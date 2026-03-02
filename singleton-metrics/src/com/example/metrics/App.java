package com.example.metrics;

import java.util.Map;

/**
 * PulseMeter CLI entry point.
 *
 * Starter behavior prints instance identity and increments a counter.
 * After you fix the Singleton, the identity should be stable across the app.
 */
public class App {

    public static void main(String[] args) throws Exception {
        String propsPath = "metrics.properties";

        MetricsLoader loader = new MetricsLoader();
        MetricsRegistry loaded = loader.loadFromFile(propsPath);

        MetricsRegistry global = MetricsRegistry.getInstance();

        System.out.println("loaded instance : " + System.identityHashCode(loaded));
        System.out.println("global instance : " + System.identityHashCode(global));
        System.out.println("same? " + (loaded == global));

        global.increment("REQUESTS_TOTAL");
        System.out.println("REQUESTS_TOTAL = " + global.getCount("REQUESTS_TOTAL"));

        System.out.println("\nall counters:");
        for (Map.Entry<String, Long> e : global.getAll().entrySet()) {
            System.out.println("  " + e.getKey() + " = " + e.getValue());
        }
    }
}
