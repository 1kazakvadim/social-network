package by.sam_solutions.kazak.social_network.dao;

import java.util.List;

public interface IAbstractBaseDao<T> {

  void saveOrUpdate(T obj);

  T getById(Long id);

  List<T> getAll();

  void deleteById(Long id);

}
