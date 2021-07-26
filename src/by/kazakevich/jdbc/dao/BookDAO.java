package by.kazakevich.jdbc.dao;

import by.kazakevich.jdbc.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends AbstractDAO<Integer, Book> {

  private final Connection connection;

  private final static String All_BOOKS_FROM_CURRENT_AND_PAST_YEAR
      = "select * from Book where year(yearOfPublish) between 2020 and 2021";

  private final static String SQL_DELETE_BOOKS = "delete from Book where year(yearOfPublish) > (?)";
  private final static String DELETE_BY_ID = "delete from Book where id = ?";
  private final static String INSERT_ENTITY = "insert into Book(title, yearOfPublish, publisher, author_id) values(?, ?, ?, ?)";
  private final static String SELECT_ALL_BOOKS = "select * from Book";
  private final static String FIND_BY_ID = "select * from Book where id = (?)";
  private final static String UPDATE_ENTITY = "update Book set title = ?, yearOfPublish = ?, publisher = ? where id = ?";

  public BookDAO(final Connection connection) {
    this.connection = connection;
  }

  //   Найти все книги, вышедшие в текущем и прошлом году.
  public List<Book> getAllBooksFromCurrentAndPastYear() {
    List<Book> result = new ArrayList<>();
    try (PreparedStatement statement = connection
        .prepareStatement(All_BOOKS_FROM_CURRENT_AND_PAST_YEAR)) {
      final ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Book model = new Book();
        model.setId(rs.getInt("id"));
        model.setTitle(rs.getString("title"));
        model.setPublisher(rs.getString("publisher"));
        model.setYearOfPublish(rs.getDate("yearOfPublish"));
        result.add(model);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  // • Удалить все книги, публикация которых была позднее заданного года
  public void deleteBooks(int date) {
    try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BOOKS)) {
      statement.setInt(1, date);
      int rs = statement.executeUpdate();
      System.out.println("Completed successfully.");
      System.out.print(rs + " the number of columns affected by the query");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Book> findAll() {
    List<Book> result = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOKS)) {
      final ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Book model = new Book();
        model.setId(rs.getInt("id"));
        model.setTitle(rs.getString("title"));
        model.setPublisher(rs.getString("publisher"));
        model.setYearOfPublish(rs.getDate("yearOfPublish"));
        model.setAuthorId(rs.getInt("author_id"));
        result.add(model);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  @Override
  public Book findEntityById(Integer id) {
    Book result = new Book();
    try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
      statement.setInt(1, id);
      final ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        result.setId(rs.getInt("id"));
        result.setTitle(rs.getString("title"));
        result.setPublisher(rs.getString("publisher"));
        result.setYearOfPublish(rs.getDate("yearOfPublish"));
        result.setAuthorId(rs.getInt("author_id"));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  @Override
  public boolean delete(Integer id) {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
      statement.setInt(1, id);
      int rs = statement.executeUpdate();
      System.out.println("Completed successfully.");
      System.out.print(rs + " the number of columns affected by the query");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }

    return true;
  }

  @Override
  public boolean create(Book entity) {
    try (PreparedStatement statement = connection.prepareStatement(INSERT_ENTITY)) {
      statement.setString(1, entity.getTitle());
      statement.setDate(2, entity.getYearOfPublish());
      statement.setString(3, entity.getPublisher());
      statement.setInt(4, entity.getAuthorId());
      int rs = statement.executeUpdate();
      System.out.println("Completed successfully.");
      System.out.print(rs + " the number of columns affected by the query");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }

    return true;
  }

  @Override
  public Book update(Book entity) {
    try (PreparedStatement statement = connection.prepareStatement(UPDATE_ENTITY)) {
      statement.setString(1, entity.getTitle());
      statement.setDate(2, entity.getYearOfPublish());
      statement.setString(3, entity.getPublisher());
      statement.setInt(4, entity.getId());
      int rs = statement.executeUpdate();
      System.out.println("Completed successfully.");
      System.out.print(rs + " the number of columns affected by the query");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }

    return entity;
  }
}

