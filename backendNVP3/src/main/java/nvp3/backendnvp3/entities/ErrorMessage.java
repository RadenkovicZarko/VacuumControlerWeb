package nvp3.backendnvp3.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ErrorMessage implements Serializable {
    public ErrorMessage(String action, Date date, int vacuumCleanerId, int owner, String message) {
        this.action = action;
        this.date = date;
        this.vacuumCleanerId = vacuumCleanerId;
        this.owner = owner;
        this.message = message;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false, name = "action")
    private String action;

    @Column(nullable = false, name = "date")
    private Date date;

    @Column(nullable = false, name = "vacuumCleanerId")
    private int vacuumCleanerId;

    @Column(nullable = false, name = "owner")
    private int owner;

    @Column(nullable = false, name = "message")
    private String message;


    public ErrorMessage() {

    }
}

