package nvp3.backendnvp3.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;



import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "first_name", length = 32)
    private String firstName;

    @Column(nullable = false, name = "last_name", length = 32)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JsonBackReference
    @JoinTable(
            name = "User_Permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Collection<Permision> permissions = new ArrayList<>(); // Set of permissions



    @OneToMany(mappedBy = "addedBy")
    @JsonBackReference
    private Collection<VacuumCleaner> vacuumCleaners = new ArrayList<>();


}
