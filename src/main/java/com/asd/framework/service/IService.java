package com.asd.framework.service;

import java.util.List;

/**
 * Created by Crawlers on 8/15/2016.
 */
public interface IService<T> {
  public Long insert(T t);

  public void addList(List<T> l);

  public Integer update(T t, Long id, boolean validate);

  public List<T> getAll(String search, List<String> searchFields, String offset, String limit);

  public T getbyid(Long id);

  public Long delete(Long id);

}
