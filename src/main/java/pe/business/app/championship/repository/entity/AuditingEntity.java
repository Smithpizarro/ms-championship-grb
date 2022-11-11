package pe.business.app.championship.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@MappedSuperclass
public class AuditingEntity implements Serializable, BaseEntity {


    @Column(name = "created_user", nullable = false,
            updatable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false,
            updatable = false)
    private Date createdDate;

    @Version
    @Column(name = "row_version")
    @JsonIgnore
    private Long rowVersion;
}
