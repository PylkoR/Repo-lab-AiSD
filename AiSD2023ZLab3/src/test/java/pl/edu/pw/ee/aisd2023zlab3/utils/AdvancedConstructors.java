package pl.edu.pw.ee.aisd2023zlab3.utils;

import pl.edu.pw.ee.aisd2023zlab3.HashOpenAddressing;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AdvancedConstructors {
    public static HashTable<String> createHashInstance(Class<? extends HashOpenAddressing> hashTableClass) {
        HashTable<String> hashInstance;

        try {
            hashInstance = (HashTable) hashTableClass.newInstance();

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException("Cannot create instance by reflection!", e);
        }

        return hashInstance;
    }

    public static HashTable<String> createHashInstance(int size, Class<? extends HashOpenAddressing> hashTableClass) {
        Class<?>[] typeOfArgs = {int.class};
        Object[] args = {size};
        HashTable<String> hashInstance;

        Constructor<?> constructor;

        try {
            constructor = hashTableClass.getConstructor(typeOfArgs);

            hashInstance = (HashTable) constructor.newInstance(args);

        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException("Not found constructor by reflection!", e);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throwIllegalArgExceptionIfIsCauseOfException(e);

            throw new RuntimeException("Cannot create instance by reflection!", e);
        }

        return hashInstance;
    }

    private static void throwIllegalArgExceptionIfIsCauseOfException(Exception e) {
        if ((e.getCause() != null) && (e.getCause() instanceof IllegalArgumentException)) {
            throw new IllegalArgumentException(e.getCause().getMessage());
        }
    }
}
