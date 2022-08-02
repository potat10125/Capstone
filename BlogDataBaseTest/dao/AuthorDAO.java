package SammyChan.BlogDataBaseTest.dao;

import SammyChan.BlogDataBaseTest.model.Author;
import java.util.List;

/**
 *
 * @author sammychan
 */
public interface AuthorDAO {
    Author addAuthor(Author author);
    void deleteAuthorByUsername(String username);
    Author getAuthor(String username);
    List<Author> getAllAuthors();
}
