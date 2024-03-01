package nvp3.backendnvp3.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public  abstract class AbstractRepository<T extends Serializable> implements Repository<T> {

    @PersistenceContext
    protected EntityManager entityManager;// = Persistence.createEntityManagerFactory("default").createEntityManager();

    private Class<T> clazz;

    public AbstractRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> getAll() {
        return entityManager.createQuery(String.format("from %s", this.clazz.getSimpleName())).getResultList();
    }

    @Override
    public T findById(int id) {
        return entityManager.find(this.clazz, id);
    }

    @Override
    @Transactional
    public T add(T object) {
        if(object == null) {
            return null;
        }
        entityManager.persist(object);
//        entityManager.clear();
//        ((User)object).setFirstName("pera");
        return object;
    }

    @Override
    public boolean removeById(int id) {
        try {
            entityManager.remove(findById(id));
        } catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(T object) {
        try {
            object = entityManager.merge(object);
        } catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

}
