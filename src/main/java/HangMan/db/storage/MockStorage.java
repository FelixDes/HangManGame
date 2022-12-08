package HangMan.db.storage;

import org.di.annotations.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MockStorage<T> implements Storage<T> {
    private final Map<String, T> table = new ConcurrentHashMap<>();
    private final Set<String> ids = new HashSet<>();

    @Override
    public Map<String, T> getAll() {
        return table;
    }

    @Override
    public T getForId(String id) {
        return table.get(id);
    }
//
//    @Override
//    public void save(String id, T user) {
//        ids.add(id);
//        table.put(id, user);
//    }

    @Override
    public void delete(String id) {
        table.remove(id);
        ids.remove(id);
    }

    @Override
    public void update(String id, T t) {
        if (ids.contains(id)) {
            table.replace(id, t);
        } else {
            table.put(id, t);
            ids.add(id);
        }

    }
}
