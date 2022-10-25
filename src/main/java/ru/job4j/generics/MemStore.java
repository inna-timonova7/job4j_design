package ru.job4j.generics;

import java.util.*;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        Map<String, T> map = new HashMap<>();
        if (!storage.containsKey(model.getId())) {
            storage.put(model.getId(), model);
        }

    }

    @Override
    public boolean replace(String id, T model) {
        return storage.replace(id, model) != null;
    }

    @Override
    public boolean delete(String id) {
        for (Map.Entry<String, T> pair : storage.entrySet()) {
            if (storage.containsKey(id)) {
                storage.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (Map.Entry<String, T> pair : storage.entrySet()) {
            if (storage.containsKey(id)) {
                return storage.get(id);
            }
        }
        return null;
    }
}
