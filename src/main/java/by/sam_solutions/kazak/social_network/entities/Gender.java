package by.sam_solutions.kazak.social_network.entities;

public enum Gender {

  MALE("MALE"),
  FEMALE("FEMALE");

  private final String name;

  Gender(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

}
