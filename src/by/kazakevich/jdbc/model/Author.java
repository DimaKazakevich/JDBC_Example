package by.kazakevich.jdbc.model;

public class Author extends Entity {

  private String fullName;
  private String country;

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setCountry(String dateOfBirth) {
    this.country = dateOfBirth;
  }

  public String getFullName() {
    return fullName;
  }

  public String getCountry() {
    return country;
  }

  @Override
  public String toString() {
    return "Author{" +
        "fullName='" + fullName + '\'' +
        ", country='" + country + '\'' +
        '}';
  }
}
