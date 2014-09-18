package ar.edu.utn.aadeed.session;

import com.google.common.collect.Lists;
import java.util.List;

public class MemorySession<T> implements Session<T> {

    private static MemorySession instance = null;

    private List<T> livingObjects = Lists.newArrayList();

    private MemorySession() {
    }

    public static MemorySession getInstance() {
        if (instance == null) {
            synchronized (MemorySession.class) {
                if (instance == null) {
                    instance = new MemorySession();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean add(T object) {
        return livingObjects.add(object);
    }

    @Override
    public boolean remove(T object) {
        return livingObjects.remove(object);
    }
}