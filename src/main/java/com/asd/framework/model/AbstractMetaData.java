package com.asd.framework.model;


import java.io.Serializable;

/**
 * Created by Crawlers on 8/15/2016.
 */
public abstract class AbstractMetaData implements Serializable{
  public static final long serialVersionUID = 7526472295622776147L;
  private Long id;
  private String createdAt;
  private String updatedAt;
  private Integer createdBy;
  private Integer updatedBy;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCreatedAt() {

    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Integer getCreatedBy() {
    if (createdBy == null){
      createdBy = 1;
    }
    return createdBy;
  }

  public Integer getUpdatedBy() {
    return updatedBy;
  }
}
