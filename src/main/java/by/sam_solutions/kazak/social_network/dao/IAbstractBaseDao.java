package by.sam_solutions.kazak.social_network.dao;

import java.util.List;

public interface IAbstractBaseDao<T> {

  void save(T obj);

  T getById(int id);

  List<T> getAll();

  void update(T obj);

  void deleteById(int id);

}
