package nvp3.backendnvp3.repositories;

import nvp3.backendnvp3.entities.ErrorMessage;
import nvp3.backendnvp3.entities.User;
import nvp3.backendnvp3.entities.VacuumCleaner;

import javax.ejb.Singleton;

@Singleton
public class ErrorMessageRepository extends AbstractRepository<ErrorMessage>{

    public ErrorMessageRepository() {
        super(ErrorMessage.class);
    }



}
