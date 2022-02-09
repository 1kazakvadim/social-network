package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Gender;
import java.time.LocalDate;

public interface BasicInformationService {

  void saveOrUpdate(BasicInformation basicInformation);

  BasicInformation getById(Long id);

  boolean isGenderValid(Gender gender);

  boolean isBirthdayDateValid(LocalDate birthday);

}
