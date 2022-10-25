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
        return storage.remove(id) != null;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
