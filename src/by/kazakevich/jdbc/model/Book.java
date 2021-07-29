package by.kazakevich.jdbc.model;

import java.sql.Date;

public class Book extends Entity {

  private String title;
  private Date yearOfPublish;
  private String publisher;
  private Integer authorId;

  public Book() {
  }

  public Book(String title, Date yearOfPublish, String publisher, Integer authorId) {
    this.title = title;
    this.yearOfPublish = yearOfPublish;
    this.publisher = publisher;
    this.authorId = authorId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setYearOfPublish(Date yearOfPublish) {
    this.yearOfPublish = yearOfPublish;
  }

  public Date getYearOfPublish() {
    return yearOfPublish;
  }

  public Integer getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Integer authorId) {
    this.authorId = authorId;
  }

  @Override
  public String toString() {
    return "Book{" +
        "title='" + title + '\'' +
        ", yearOfPublish=" + yearOfPublish +
        ", publisher='" + publisher + '\'' +
        ", authorId=" + authorId +
        '}';
  }

  public static class Builder {

    private Book book;

    public Builder() {
      book = new Book();
    }

    public Builder setTitle(String title) {
      book.title = title;
      return this;
    }

    public Builder setYearOfPublish(Date yearOfPublish) {
      book.yearOfPublish = yearOfPublish;
      return this;
    }

    public Builder setPublisher(String publisher) {
      book.publisher = publisher;
      return this;
    }

    public Builder setAuthorId(Integer authorId) {
      book.authorId = authorId;
      return this;
    }

    public Book build() {
      return book;
    }
  }
}