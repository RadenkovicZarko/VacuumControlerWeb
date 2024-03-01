package nvp3.backendnvp3.repositories;

import nvp3.backendnvp3.entities.VacuumCleaner;

import javax.ejb.Singleton;
import javax.persistence.OptimisticLockException;
import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class VacuumCleanerRepository extends AbstractRepository<VacuumCleaner>{

    public VacuumCleanerRepository(){super(VacuumCleaner.class);}


    public VacuumCleaner findByIdVacuumCleaner(int id)
    {
        TypedQuery<VacuumCleaner> query = this.entityManager
                .createQuery("SELECT vc FROM VacuumCleaner vc WHERE vc.id = :vacuumCleanerId", VacuumCleaner.class);
        query.setParameter("vacuumCleanerId",id);

        for(VacuumCleaner vc:query.getResultList())
        {
            return vc;
        }

        return null;
    }

    public VacuumCleaner save(VacuumCleaner vc) throws OptimisticLockException{
            VacuumCleaner existingVacuumCleaner = entityManager.find(VacuumCleaner.class, vc.getId());
            if (existingVacuumCleaner != null && existingVacuumCleaner.getVersion() > vc.getVersion()) {
                throw new OptimisticLockException("Object version mismatch");
            }
            vc = entityManager.merge(vc);
            return vc;
    }


    public VacuumCleaner myRemoveById(int id)
    {
        this.entityManager.createNativeQuery("UPDATE vacuumCleaner SET active = :active WHERE id = :id")
                .setParameter("active", false)
                .setParameter("id",id)
                .executeUpdate();

        return this.findById(id);
    }






}
