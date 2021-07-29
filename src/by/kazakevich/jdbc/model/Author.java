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

  public static class Builder {

    private Author author;

    public Builder() {
      author = new Author();
    }

    public Builder setFullName(String fullName) {
      author.fullName = fullName;
      return this;
    }

    public Builder setCountry(String country) {
      author.country = country;
      return this;
    }

    public Author build() {
      return author;
    }
  }
}
