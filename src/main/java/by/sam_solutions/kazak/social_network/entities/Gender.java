package by.sam_solutions.kazak.social_network.entities;

public enum Gender {

  NOT_KNOWN("NOT_KNOWN"),
  MALE("MALE"),
  FEMALE("FEMALE"),
  NOT_APPLICABLE("NOT_APPLICABLE");

  private final String name;

  Gender(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

}
