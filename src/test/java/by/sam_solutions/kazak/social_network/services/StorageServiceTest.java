package by.sam_solutions.kazak.social_network.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.services.impl.StorageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppContextConfig.class)
public class StorageServiceTest {

  private final Logger logger = LoggerFactory.getLogger(StorageServiceTest.class);

  @Autowired
  private StorageService storageService;

  @Test
  public void testUpload() {
    logger.debug("Execute test: testUpload()");
    storageService = mock(StorageServiceImpl.class);
    MultipartFile multipartFile = mock(MultipartFile.class);
    when(storageService.upload(multipartFile)).thenReturn("uploadName");
    assertThat(storageService.upload(multipartFile)).isEqualTo("uploadName");
  }

  @Test
  public void testDelete() {
    logger.debug("Execute test: testDelete()");
    storageService = mock(StorageServiceImpl.class);
    when(storageService.delete("deleteName")).thenReturn("deleteName");
    assertThat(storageService.delete("deleteName")).isEqualTo("deleteName");
  }

  @Test
  public void testIsMultipartFileBMPValid() {
    logger.debug("Execute test: testIsMultipartFileBMPValid()");
    MockMultipartFile multipartFile
        = new MockMultipartFile("test_image", "test_image", "image/bmp", new byte[10]);
    assertTrue(storageService.isMultipartFileValid(multipartFile));
  }

  @Test
  public void testIsMultipartFilePNGValid() {
    logger.debug("Execute test: testIsMultipartFilePNGValid()");
    MockMultipartFile multipartFile
        = new MockMultipartFile("test_image", "test_image", "image/png", new byte[10]);
    assertTrue(storageService.isMultipartFileValid(multipartFile));
  }

  @Test
  public void testIsMultipartFileJPEGValid() {
    logger.debug("Execute test: testIsMultipartFileJPEGValid()");
    MockMultipartFile multipartFile
        = new MockMultipartFile("test_image", "test_image", "image/jpeg", new byte[10]);
    assertTrue(storageService.isMultipartFileValid(multipartFile));
  }

  @Test
  public void testIsMultipartFileInvalid() {
    logger.debug("Execute test: testIsMultipartFileInvalid()");
    MockMultipartFile multipartFile
        = new MockMultipartFile("test_image", "test_image", "image/txt", new byte[10]);
    assertFalse(storageService.isMultipartFileValid(multipartFile));
  }

}
