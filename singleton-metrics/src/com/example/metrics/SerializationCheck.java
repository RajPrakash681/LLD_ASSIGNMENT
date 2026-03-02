package com.example.metrics;

import java.io.*;

/**
 * Serializes and deserializes the registry.
 * Starter will typically produce a NEW instance. After fix, it must return the same singleton.
 */
public class SerializationCheck {

    public static void main(String[] args) throws Exception {
        MetricsRegistry a = MetricsRegistry.getInstance();
        a.setCount("REQUESTS_TOTAL", 42);

        byte[] bytes = serialize(a);
        MetricsRegistry b = deserialize(bytes);

        System.out.println("a identity: " + System.identityHashCode(a));
        System.out.println("b identity: " + System.identityHashCode(b));
        System.out.println("same? " + (a == b));
        System.out.println("b REQUESTS_TOTAL = " + b.getCount("REQUESTS_TOTAL"));
    }

    private static byte[] serialize(MetricsRegistry r) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(r);
        }
        return baos.toByteArray();
    }

    private static MetricsRegistry deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return (MetricsRegistry) ois.readObject();
        }
    }
}
