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
@Table(name="tbl_player")
public class Player extends AuditingEntity implements Serializable,BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede ser vacío")
    @Column(name="name", nullable=false)
    private String name;

    @NotEmpty(message = "El gender no puede ser vacío")
    @Column(name="gender", nullable=false)
    private String gender;


    @NotNull(message = "El skill no puede ser vacío")
    @Column(name="skill", nullable=false)
    private Long skill;

    @NotNull(message = "El strength no puede ser vacío")
    @Column(name="strength", nullable=false)
    private Long strength;

    @NotNull(message = "El speed no puede ser vacío")
    @Column(name="speed", nullable=false)
    private Long speed;

    @PrePersist
    public void prePersist() {
        this.setCreatedDate(new Date());
    }

}
