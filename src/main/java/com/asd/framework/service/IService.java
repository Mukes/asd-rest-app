package com.asd.framework.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Crawlers on 8/15/2016.
 */
public interface IService<T> {
  public Object insert(T t);

  public void addList(List<T> l);

  public Object update(T t, Long id, boolean validate);

  public List<T> getAll(String search, List<String> searchFields, String offset, String limit);

  public T getbyid(Long id);

  public Long delete(Long id);

  List<T> customGetAll(Map<String, String> conditionMap);

}
