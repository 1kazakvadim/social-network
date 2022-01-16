package by.sam_solutions.kazak.social_network.services.impl;

import static org.apache.http.entity.ContentType.IMAGE_BMP;
import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.apache.http.entity.ContentType.IMAGE_PNG;

import by.sam_solutions.kazak.social_network.services.StorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

  private final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

  @Autowired
  private AmazonS3 amazonS3;

  @Value("${aws.bucket.name}")
  private String bucketName;

  @Override
  public String upload(MultipartFile file) {
    File convertedFile = convertMultipartFileToFile(file);
    String fileName = UUID.randomUUID().toString();
    amazonS3.putObject(bucketName, fileName, convertedFile);
    convertedFile.delete();
    logger.debug("Uploaded file with name {}", fileName);
    return fileName;
  }

  @Override
  public byte[] download(String fileName) {
    S3Object s3Object = amazonS3.getObject(bucketName, fileName);
    S3ObjectInputStream inputStream = s3Object.getObjectContent();
    try {
      return IOUtils.toByteArray(inputStream);
    } catch (IOException exp) {
      logger.debug("Downloaded file with name {}", fileName);
      exp.printStackTrace();
    }
    return null;
  }

  @Override
  public String delete(String fileName) {
    amazonS3.deleteObject(bucketName, fileName);
    logger.debug("Deleted file with name {}", fileName);
    return fileName;
  }

  @Override
  public boolean isMultipartFileValid(MultipartFile file) {
    return Arrays.asList(IMAGE_PNG.getMimeType(),
        IMAGE_BMP.getMimeType(),
        IMAGE_JPEG.getMimeType()).contains(file.getContentType());
  }

  private File convertMultipartFileToFile(MultipartFile file) {
    File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
    try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
      fos.write(file.getBytes());
    } catch (IOException exp) {
      logger.error("Error converting MultipartFile to File");
      exp.printStackTrace();
    }
    return convertedFile;
  }

}
