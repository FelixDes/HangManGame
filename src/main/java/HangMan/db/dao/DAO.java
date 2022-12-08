package HangMan.db.dao;

import java.util.List;
import java.util.Map;

public interface DAO<T> {
    Map<String, T> getAll();

    T getForId(String id);

//    String save(T t);

    void delete(String id);

    void update(String id, T t);
}
