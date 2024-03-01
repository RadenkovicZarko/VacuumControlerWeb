package nvp3.backendnvp3.services;


import nvp3.backendnvp3.repositories.AbstractRepository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T extends Serializable, R extends AbstractRepository<T>> implements Service<T> {

    public abstract R getRepository();

    @Override
    public T add(T object) {
        return this.getRepository().add(object);
    }

    @Override
    public boolean removeById(int id) {
        return this.getRepository().removeById(id);
    }

    @Override
    public boolean update(T object) {
        return this.getRepository().update(object);
    }

    @Override
    public List<T> getAll() {
        return this.getRepository().getAll();
    }

    @Override
    public T findById(int id) {
        return this.getRepository().findById(id);
    }
}

