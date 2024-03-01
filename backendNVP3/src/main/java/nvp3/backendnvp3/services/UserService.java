package nvp3.backendnvp3.services;

import nvp3.backendnvp3.entities.Status;
import nvp3.backendnvp3.entities.User;
import nvp3.backendnvp3.entities.VacuumCleaner;
import nvp3.backendnvp3.repositories.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
public class UserService extends AbstractService<User, UserRepository> {
    @EJB
    private UserRepository userRepository;
    @Override
    public UserRepository getRepository() {
        return this.userRepository;
    }

    public User findUser(String email, String password){
        return userRepository.findUser(email, password);
    }



}
