package by.kazakevich.jdbc;

import by.kazakevich.jdbc.dao.AuthorDAO;
import by.kazakevich.jdbc.dao.BookDAO;
import by.kazakevich.jdbc.model.Book;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException, ClassNotFoundException {

    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_BLACK = "\u001B[30m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";

    Class.forName("com.mysql.cj.jdbc.Driver");
    String connectionString = "jdbc:mysql://localhost:3306/JDBC?serverTimezone=UTC";

    try (Connection connection = DriverManager.getConnection(
        connectionString,
        System.getenv("DB_USERNAME"),
        System.getenv("DB_PASSWORD"))) {

      BookDAO bookDao = new BookDAO(connection);
      AuthorDAO authorDao = new AuthorDAO(connection);

      /**
       * FIND ALL BOOKS
       */
      for (var item : bookDao.findAll()) {
        System.out.println(ANSI_GREEN + item);
      }

      /**
       * FIND BOOK BY ID
       */
      /*System.out.println(ANSI_RED + bookDao.findEntityById(5));*/

      /**
       * DELETE BOOK
       */
        /*System.out.println(ANSI_CYAN + bookDao.delete(5));

        for (var item : bookDao.findAll()) {
            System.out.println(ANSI_GREEN + item);
        }*/

      /**
       * INSERT BOOK
       */
/*        Book newBook = new Book("Мастер и Маргарита", new Date(1627195584825L), "ПИТЕР", 2);
        System.out.println(ANSI_CYAN + bookDao.create(newBook));

        for (var item: bookDao.findAll()) {
            System.out.println(ANSI_GREEN + item);
        }*/

      /**
       * UPDATE BOOK
       */
/*        Book bookToUpdate = new Book("Идиот123", new Date(1627195584825L), "ПИТЕР", 1);
        bookToUpdate.setId(6);

        System.out.println(ANSI_CYAN + bookDao.update(bookToUpdate));
        for (var item: bookDao.findAll()) {
            System.out.println(ANSI_PURPLE + item);
        }*/

      /**
       * GET ALL BOOKS WHICH PUBLISHED IN THE PAST AND CURRENT YEAR
       */
/*        for (var item : bookDao.getAllBooksFromCurrentAndPastYear()) {
            System.out.println(ANSI_PURPLE + item);
        }*/

      /**
       * DELETE BOOKS WHICH PUBLISHED LATER THAN PARAM
       */
      /*bookDao.deleteBooks(1960);*/

      /**
       * MIN N BOOK
       */

/*        for (var item: authorDao.getAuthorsWhyWritedMinNBooks(1)) {
            System.out.println(ANSI_PURPLE + item);
        }*/
    }
  }
}