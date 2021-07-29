package by.kazakevich.jdbc.dao;

import by.kazakevich.jdbc.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends AbstractDAO<Integer, Book> {

  private final Connection connection;

  public BookDAO(final Connection connection) {
    this.connection = connection;
  }

  //   Найти все книги, вышедшие в текущем и прошлом году.
  public List<Book> getAllBooksFromCurrentAndPastYear() {
    List<Book> result = new ArrayList<>();
    String query = "select * from Book where year(yearOfPublish) between 2020 and 2021";
    return executeQuery(result, query);
  }

  // • Удалить все книги, публикация которых была позднее заданного года
  public void deleteBooks(int date) {
    String query = "delete from Book where year(yearOfPublish) > (?)";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, date);
      int rs = statement.executeUpdate();
      System.out.print(rs + " row(s) affected by the query");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Book> findAll() {
    List<Book> result = new ArrayList<>();
    String query = "select * from Book";
    return executeQuery(result, query);
  }

  @Override
  public Book findEntityById(Integer id) {
    Book result = null;
    String query = "select * from Book where id = (?)";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      setIdParamToStatement(statement, id);
      final ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        result = getInitializedBook(rs);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  @Override
  public boolean delete(Integer id) {
    String query = "delete from Book where id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      setIdParamToStatement(statement, id);
      int rs = statement.executeUpdate();
      System.out.print(rs + " row(s) affected by the query");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }

    return true;
  }

  @Override
  public boolean create(Book entity) {
    String query = "insert into Book(title, yearOfPublish, publisher, author_id) values(?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      setAllParamsToStatement(statement, entity);
      int rs = statement.executeUpdate();
      System.out.print(rs + " row(s) affected by the query");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }

    return true;
  }

  @Override
  public Book update(Book entity) {
    String query = "update Book set title = ?, yearOfPublish = ?, publisher = ? where id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      setAllParamsToStatement(statement, entity);
      int rs = statement.executeUpdate();
      System.out.print(rs + " row(s) affected by the query");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }

    return entity;
  }

  private List<Book> executeQuery(List<Book> result, String query) {
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      final ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Book model = getInitializedBook(rs);
        result.add(model);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  private Book getInitializedBook(ResultSet resultSet) throws SQLException {
    return new Book.Builder()
        .setTitle(resultSet.getString("title"))
        .setPublisher(resultSet.getString("publisher"))
        .setYearOfPublish(resultSet.getDate("yearOfPublish"))
        .setAuthorId(resultSet.getInt("author_id"))
        .build();
  }

  private void setIdParamToStatement(PreparedStatement statement, Integer id)
      throws SQLException {
    statement.setInt(1, id);
  }

  private void setAllParamsToStatement(PreparedStatement statement, Book entity)
      throws SQLException {
    statement.setString(1, entity.getTitle());
    statement.setDate(2, entity.getYearOfPublish());
    statement.setString(3, entity.getPublisher());
    statement.setInt(4, entity.getId());
  }
}

