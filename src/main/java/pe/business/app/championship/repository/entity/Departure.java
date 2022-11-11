package pe.business.app.championship.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="tbl_departure")
public class Departure extends AuditingEntity implements Serializable,BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "el tournamentId no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Tournament tournament;

    @NotNull(message = "el tournamentId no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Player player;

    @NotEmpty(message = "El result no puede ser vacío")
    @Column(name="result", nullable=false)
    private String result;


    @PrePersist
    public void prePersist() {
        this.setCreatedDate(new Date());
    }

}
