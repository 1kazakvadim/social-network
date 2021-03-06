package by.sam_solutions.kazak.social_network.services;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

  String upload(MultipartFile file);

  String delete(String fileName);

  boolean isMultipartFileValid(MultipartFile file);

}
