package nvp3.backendnvp3.repositories;

import java.io.Serializable;
import java.util.List;

public interface Repository <T extends Serializable>{
    public T add(T object);
    public boolean removeById(int id);
    public boolean update(T object);
    public List<T> getAll();
    public T findById(int id);
}
