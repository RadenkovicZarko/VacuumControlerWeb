package nvp3.backendnvp3.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VacuumCleaner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "name")    private String name;

    @Column(nullable = false, name = "status")    private Status status;

    @Column(nullable = false, name = "createDate")    private Date createDate;

    @Column(nullable = false, name = "active")
    private Boolean active;

    @Column(nullable = false, name = "busy")
    private Boolean busy;

    @Column(nullable = false, name = "used")
    private Integer used;

    @Version
    private Integer version = 0;

    @ManyToOne
    private User addedBy;


}
