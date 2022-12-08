package HangMan.db.storage;

import java.util.Map;

public interface Storage<T> {
    Map<String, T> getAll();

    T getForId(String id);

//    void save(String id, T t);

    void delete(String id);

    void update(String id, T t);
}
