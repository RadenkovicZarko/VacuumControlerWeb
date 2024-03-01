package nvp3.backendnvp3.repositories;


import nvp3.backendnvp3.entities.Permision;
import nvp3.backendnvp3.entities.User;
import nvp3.backendnvp3.entities.VacuumCleaner;
import org.mindrot.jbcrypt.BCrypt;


import javax.ejb.Singleton;
import javax.jms.Message;
import javax.persistence.OptimisticLockException;
import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super(User.class);
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean verifyPassword(String candidatePassword, String hashedPassword) {
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }

    public User findUser(String email, String password) {

            TypedQuery<User> query = this.entityManager
                    .createQuery("select u from User u where u.email like :email", User.class);
            query.setParameter("email",email);

            User user=new User();
            for(User u:query.getResultList())
            {
                if(verifyPassword(password, u.getPassword()))
                    return u;
            }
            return null;
    }

    @Override
    public User add(User u)
    {
        this.entityManager.createNativeQuery("INSERT into user (first_name,last_name,email,password) values (:firstName, :lastName, :email, :password)")
                .setParameter("firstName", u.getFirstName())
                .setParameter("lastName", u.getLastName())
                .setParameter("email", u.getEmail())
                .setParameter("password", hashPassword(u.getPassword()))
                .executeUpdate();

        TypedQuery<User> query = this.entityManager
                .createQuery("select u from User u where u.email like :email", User.class);
        query.setParameter("email",u.getEmail());
        User u1=query.getResultList().get(0);


        for(Permision p:u.getPermissions())
        {
            this.entityManager.createNativeQuery("INSERT INTO user_permissions(user_id,permission_id) VALUES(:generatedId, :permissionId)")
                    .setParameter("generatedId",u1.getId())
                    .setParameter("permissionId",p.getId())
                    .executeUpdate();
        }
        return u;
    }


    @Override
    public boolean update(User u)
    {
        this.entityManager.createNativeQuery("UPDATE user SET first_name = :firstName, last_name = :lastName, email = :email WHERE id = :id")
                .setParameter("firstName", u.getFirstName())
                .setParameter("lastName", u.getLastName())
                .setParameter("email", u.getEmail())
                .setParameter("id",u.getId())
                .executeUpdate();
        this.entityManager.createNativeQuery("DELETE FROM user_permissions WHERE user_id = :id").setParameter("id",u.getId()).executeUpdate();

        for(Permision p:u.getPermissions())
        {
            this.entityManager.createNativeQuery("INSERT INTO user_permissions(user_id,permission_id) VALUES(:id, :permissionId)")
                    .setParameter("id",u.getId())
                    .setParameter("permissionId",p.getId())
                    .executeUpdate();
        }
        return true;
    }


    @Override
    public boolean removeById(int id) {
        this.entityManager.createNativeQuery("DELETE FROM user_permissions WHERE user_id = :id").setParameter("id",id).executeUpdate();

        this.entityManager.createNativeQuery("DELETE FROM user WHERE id = :id").setParameter("id",id).executeUpdate();
        return true;
    }




}
