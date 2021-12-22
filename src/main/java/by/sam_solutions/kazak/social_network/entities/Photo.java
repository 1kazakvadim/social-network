package by.sam_solutions.kazak.social_network.entities;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photo")
public class Photo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "image_name")
  private String imageName;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "album_id")
  private Album album;

  @Column(name = "like_count")
  private Integer likeCount;

  @Column(name = "time_creation")
  private Timestamp timeCreation;

  public Photo() {
  }

  public Photo(Long id, String imageName, String description,
      Album album, Integer likeCount, Timestamp timeCreation) {
    this.id = id;
    this.imageName = imageName;
    this.description = description;
    this.album = album;
    this.likeCount = likeCount;
    this.timeCreation = timeCreation;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }

  public Integer getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(Integer likeCount) {
    this.likeCount = likeCount;
  }

  public Timestamp getTimeCreation() {
    return timeCreation;
  }

  public void setTimeCreation(Timestamp timeCreation) {
    this.timeCreation = timeCreation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Photo photo = (Photo) o;
    return Objects.equals(id, photo.id) &&
        Objects.equals(imageName, photo.imageName) &&
        Objects.equals(description, photo.description) &&
        Objects.equals(album, photo.album) &&
        Objects.equals(likeCount, photo.likeCount) &&
        Objects.equals(timeCreation, photo.timeCreation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, imageName, description, album, likeCount, timeCreation);
  }

}
