package pe.business.app.championship.repository.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

//@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="tbl_tournament")
public class Tournament implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "El nombre no puede ser vacío")
    @Column(name="name", nullable=false)
    private String Name;

    @NotEmpty(message = "El nombre no puede ser vacío")
    @Column(name="gendertype", nullable=false)
    private String genderType;

    //@NotEmpty(message = "el cumpleaños no puede estar vacío")
    @Column(name="datetournament")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dateTournament;

}
