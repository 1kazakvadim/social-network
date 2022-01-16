package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Country;
import by.sam_solutions.kazak.social_network.facades.CountryFacade;
import by.sam_solutions.kazak.social_network.services.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryFacadeImpl implements CountryFacade {

  @Autowired
  private CountryService countryService;

  @Override
  public List<Country> getAll() {
    return countryService.getAll();
  }

}
