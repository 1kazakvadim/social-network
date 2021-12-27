package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import java.time.LocalDate;
import java.util.List;

public interface BasicInformationService {

  void saveOrUpdate(BasicInformation basicInformation);

  BasicInformation getById(Long id);

  List<BasicInformation> getAll();

  void deleteById(Long id);

  boolean isGenderValid(String gender);

  boolean isBirthdayDateValid(LocalDate birthday);

}
