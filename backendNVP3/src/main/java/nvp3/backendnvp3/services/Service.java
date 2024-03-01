package nvp3.backendnvp3.services;

import java.io.Serializable;
import java.util.List;

public interface Service<T extends Serializable> {
    T add(T object);
    boolean removeById(int id);
    boolean update(T object);
    List<T> getAll();
    T findById(int id);
}
