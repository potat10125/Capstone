package SammyChan.BlogDataBaseTest.dao;

import SammyChan.BlogDataBaseTest.model.Author;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author sammychan
 */
@SpringBootTest
public class AuthorDAODBTest {
    @Autowired
    AuthorDAO dao;
    
    public AuthorDAODBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
        
    }

    @Test
    public void testAll() {
        Author author1 = new Author();
        author1.setUsername("TestUser1");
        author1.setDisplayName("Test Name 1");
        author1.setPass("TestPassword1");
        author1.setPermissions(false);
        
        author1 = dao.addAuthor(author1);
        Author fromDao = dao.getAuthor("TestUser1");
        
        assertTrue(author1.equals(fromDao));
        assertTrue(fromDao.getUsername().equals("TestUser1"));
        assertTrue(fromDao.getDisplayName().equals("Test Name 1"));
        assertTrue(fromDao.getPass().equals("TestPassword1"));
        assertFalse(fromDao.getPermissions());
        
        Author author2 = new Author();
        author2.setUsername("TestUser2");
        author2.setDisplayName("Test Name 2");
        author2.setPass("TestPassword2");
        author2.setPermissions(false);        
        author2 = dao.addAuthor(author2);
        
        List<Author> allAuthors = dao.getAllAuthors();
        assertTrue(allAuthors.contains(author1));
        assertTrue(allAuthors.contains(author2));
        
        dao.deleteAuthorByUsername(author1.getUsername());
        dao.deleteAuthorByUsername(author2.getUsername());
        
        allAuthors = dao.getAllAuthors();  
        
        assertFalse(allAuthors.contains(author1));
        assertFalse(allAuthors.contains(author2));
    }
    
}
