package nvp3.backendnvp3.authentication;


import nvp3.backendnvp3.entities.User;

import javax.ejb.Local;

@Local
public interface IAuthService {
    public String generateJWT(User user);

    public boolean isAuthorized(String token,String action);

    public int getId(String token);
}
