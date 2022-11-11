package pe.business.app.championship.repository.entity;


import java.util.Date;

public interface BaseEntity {
    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public Date getCreatedDate() ;

    public void setCreatedDate(Date createdDate);

    public Long getRowVersion() ;

    public void setRowVersion(Long rowVersion) ;
}
