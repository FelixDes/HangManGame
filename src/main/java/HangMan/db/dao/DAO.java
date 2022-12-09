package HangMan.db.dao;

import java.util.List;
import java.util.Map;

public interface DAO<T> {
    Map<String, T> getAll();

    T getForId(String id);

    void delete(String id);

    void update(String id, T t);
}
