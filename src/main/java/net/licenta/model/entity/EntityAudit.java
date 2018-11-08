package net.licenta.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author daniel.albulescu
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntityAudit{

  @UpdateTimestamp
  @Column(name = "UPDATE_DATE")
  private LocalDateTime updateDate;
  
  @LastModifiedBy
  @Column(name = "UPDATE_USER")
  private String updatedUser;

  
  @CreationTimestamp
  @Column(name = "CREATION_DATE")
  private LocalDateTime creationdDate;

  @CreatedBy
  @Column(name = "CREATION_USER")
  private String creationUser;
  
  @Version
  @Column(name = "VERSION")
  private Long version;

  public LocalDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
  }

  public String getUpdatedUser() {
    return updatedUser;
  }

  public void setUpdatedUser(String updatedUser) {
    this.updatedUser = updatedUser;
  }

  public LocalDateTime getCreationdDate() {
    return creationdDate;
  }

  public void setCreationdDate(LocalDateTime creationdDate) {
    this.creationdDate = creationdDate;
  }

  public String getCreationUser() {
    return creationUser;
  }

  public void setCreationUser(String creationUser) {
    this.creationUser = creationUser;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }
}
