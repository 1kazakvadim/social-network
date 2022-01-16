package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.converters.BasicInformationConverter;
import by.sam_solutions.kazak.social_network.dto.BasicInformationDTO;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.facades.BasicInformationFacade;
import by.sam_solutions.kazak.social_network.services.BasicInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasicInformationFacadeImpl implements BasicInformationFacade {

  private final Logger logger = LoggerFactory.getLogger(BasicInformationFacadeImpl.class);

  @Autowired
  private BasicInformationService basicInformationService;

  @Autowired
  private BasicInformationConverter basicInformationConverter;

  @Override
  public void updateBasicInformation(BasicInformationDTO basicInformationDTO) {
    BasicInformation basicInformation = new BasicInformation();
    try {
      basicInformation = (BasicInformation) basicInformationConverter.convertTargetToSourceClass(
          basicInformationDTO,
          BasicInformation.class);
    } catch (Exception exp) {
      logger.debug("Error converting {} to {}", BasicInformationDTO.class.getName(),
          BasicInformation.class.getName());
      exp.printStackTrace();
    }
    basicInformationService.saveOrUpdate(basicInformation);
  }

}
