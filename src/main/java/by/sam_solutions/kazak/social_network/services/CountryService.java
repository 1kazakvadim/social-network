package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Country;
import java.util.List;

public interface CountryService {

  void saveOrUpdate(Country country);

  Country getById(Long id);

  List<Country> getAll();

  void deleteById(Long id);

}
