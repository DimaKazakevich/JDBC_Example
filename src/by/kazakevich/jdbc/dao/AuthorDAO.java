package by.kazakevich.jdbc.dao;

import by.kazakevich.jdbc.model.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends AbstractDAO<Integer, Author> {

  private final Connection connection;

  public AuthorDAO(final Connection connection) {
    this.connection = connection;
  }

  // • Вывести информацию об авторах.
  public List<Author> authorInfo() throws SQLException {
    String query = "select * from Author";
    List<Author> result = new ArrayList<>();
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(query);
      final ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Author model = getInitializedAuthor(rs);
        result.add(model);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      statement.close();
    }
    return result;
  }

  // • Вывести информацию об авторах, написавших как минимум n книг.
  public List<Author> getAuthorsWhyWroteMinNBooks(int n) {
    String query = "select Author.authorName, Author.country\n" +
        "from Author join Book\n" +
        "on Author.id = Book.author_id\n" +
        "group by Author.authorName\n" +
        "having count(Book.id) >= ?";
    List<Author> result = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, n);
      final ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Author model = getInitializedAuthor(rs);
        result.add(model);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public List<Author> findAll() {
    return null;
  }

  @Override
  public Author findEntityById(Integer id) {
    return null;
  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }

  @Override
  public boolean create(Author entity) {
    return false;
  }

  @Override
  public Author update(Author entity) {
    return null;
  }

  private Author getInitializedAuthor(ResultSet resultSet) throws SQLException {
    return new Author.Builder()
        .setFullName(resultSet.getString("authorName"))
        .setCountry(resultSet.getString("country"))
        .build();
  }
}