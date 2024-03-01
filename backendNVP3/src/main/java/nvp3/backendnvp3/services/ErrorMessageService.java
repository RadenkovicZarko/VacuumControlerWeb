package nvp3.backendnvp3.services;

import nvp3.backendnvp3.entities.ErrorMessage;
import nvp3.backendnvp3.entities.VacuumCleaner;
import nvp3.backendnvp3.repositories.ErrorMessageRepository;
import nvp3.backendnvp3.repositories.VacuumCleanerRepository;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
@Singleton
public class ErrorMessageService extends AbstractService<ErrorMessage, ErrorMessageRepository>{

    @EJB
    private ErrorMessageRepository errorMessageRepository;

    @Override
    public ErrorMessageRepository getRepository() {
        return this.errorMessageRepository;
    }


    public List<ErrorMessage> getAllErrorMessagesForUserId(int userId)
    {
        List<ErrorMessage> lista = errorMessageRepository.getAll();

        return lista.stream()
                .filter(vc->(vc.getOwner()==userId))
                .collect(Collectors.toList());
    }
}
