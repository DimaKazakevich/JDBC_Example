package by.kazakevich.jdbc.dao;

import by.kazakevich.jdbc.model.Author;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends AbstractDAO<Integer, Author> {

  private final Connection connection;

  private final static String AUTHOR_INFO = "select * from Author";
  private final static String AUTHOR_WHY_WROTE_MIN_N_BOOKS
      = "select Author.authorName, Author.country\n" +
      "from Author join Book\n" +
      "on Author.id = Book.author_id\n" +
      "group by Author.authorName\n" +
      "having count(Book.id) >= ?";

  public AuthorDAO(final Connection connection) {
    this.connection = connection;
  }

  // • Вывести информацию об авторах.
  public List<Author> authorInfo() throws SQLException {
    List<Author> result = new ArrayList<>();
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(AUTHOR_INFO);
      final ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Author model = new Author();
        model.setFullName(rs.getString("authorName"));
        model.setCountry(rs.getString("country"));
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
    List<Author> result = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(AUTHOR_WHY_WROTE_MIN_N_BOOKS)) {
      statement.setInt(1, n);
      final ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Author model = new Author();
        model.setFullName(rs.getString("authorName"));
        model.setCountry(rs.getString("country"));
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
}