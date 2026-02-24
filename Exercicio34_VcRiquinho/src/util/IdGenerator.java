package util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

public final class IdGenerator {

    private static final Map<Class<?>, AtomicInteger> generators = new ConcurrentHashMap<>();

    private IdGenerator() { }

    public static int generate(Class<?> clazz) {
        generators.putIfAbsent(clazz, new AtomicInteger(1));
        return generators.get(clazz).getAndIncrement();
    }
}