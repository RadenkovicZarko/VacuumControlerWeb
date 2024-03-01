package nvp3.backendnvp3.util;

import org.mindrot.jbcrypt.BCrypt;

public class Main {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    public static void main(String[] args) {
        System.out.println(hashPassword("111"));
        System.out.println(hashPassword("222"));
        System.out.println(hashPassword("333"));
    }
}
