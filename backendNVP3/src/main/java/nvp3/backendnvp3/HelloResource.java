package nvp3.backendnvp3;

import org.mindrot.jbcrypt.BCrypt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


public class HelloResource {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }



    public static void main(String[] args) {
        System.out.println(hashPassword("000"));
    }

}