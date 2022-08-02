package SammyChan.BlogDataBaseTest.dao;

import SammyChan.BlogDataBaseTest.model.Author;
import SammyChan.BlogDataBaseTest.model.Blog;
import java.util.Arrays;
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
public class BlogDAODBTest {
    @Autowired
    BlogDAO dao;
    
    @Autowired
    AuthorDAO authorDao;
    
    public BlogDAODBTest() {
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
        List<Blog> blogs = dao.getAllBlogs();
        for (Blog blog : blogs){
            dao.deleteBlogById(blog.getId());
        }
    }

    /**
     * Test of getBlogById method, of class BlogDAODB.
     */
    @Test
    public void testAddAndGet() {
        Author author1 = new Author();
        author1.setUsername("TestUser1");
        author1.setDisplayName("Test Name 1");
        author1.setPass("TestPassword1");
        author1.setPermissions(false);
        author1 = authorDao.addAuthor(author1);
        
        Blog blog = new Blog();
        // INSERT CODE TO SET AUTHOR LATER
        blog.setUser(author1.getUsername());
        blog.setApproved(true);
        blog.setTitle("Test Title");
        blog.setContent("Test Content");
        blog.setPublishDate("2020-01-01 10:10:10");
        List<String> tags = Arrays.asList("Tag 1", "Tag 2");
        blog.setHashtags(tags);
        

        blog = dao.addBlog(blog);

        
        Blog fromDao = dao.getBlogById(blog.getId());
        
        assertTrue(blog.equals(fromDao));
        assertTrue(blog.getApproved());
        assertTrue(fromDao.getTitle().equals("Test Title"));
        assertTrue(fromDao.getContent().equals("Test Content"));
        assertTrue(fromDao.getPublishDate().equals("2020-01-01 10:10:10"));
        assertTrue(fromDao.getHashtags().contains("Tag 1"));
        assertTrue(fromDao.getHashtags().contains("Tag 2"));
        
        authorDao.deleteAuthorByUsername(author1.getUsername());
    }

    /**
     * Test of getAllBlogs method, of class BlogDAODB.
     */
    @Test
    public void testGetAllMethods() {
        Author author1 = new Author();
        author1.setUsername("TestUser1");
        author1.setDisplayName("Test Name 1");
        author1.setPass("TestPassword1");
        author1.setPermissions(false);
        author1 = authorDao.addAuthor(author1);   
        Author author2 = new Author();
        author2.setUsername("TestUser2");
        author2.setDisplayName("Test Name 2");
        author2.setPass("TestPassword2");
        author2.setPermissions(false);        
        author2 = authorDao.addAuthor(author2);        
        
        Blog blog1 = new Blog();
        // INSERT CODE TO SET AUTHOR LATER   
        blog1.setUser(author1.getUsername());        
        blog1.setApproved(true);
        blog1.setTitle("Test Title");
        blog1.setContent("Test Content");
        blog1.setPublishDate("2020-01-01 10:10:10");
        List<String> tags = Arrays.asList("Tag 1", "Tag 2");
        blog1.setHashtags(tags);        
        blog1 = dao.addBlog(blog1);   
        
        Blog blog2 = new Blog();
        // INSERT CODE TO SET AUTHOR LATER
        blog2.setUser(author2.getUsername());        
        blog2.setApproved(false);
        blog2.setTitle("Test Title 2");
        blog2.setContent("Test Content 2");
        blog2.setPublishDate("2022-02-02 10:10:10");
        List<String> tags2 = Arrays.asList("Tag 2");
        blog2.setHashtags(tags2);        
        blog2 = dao.addBlog(blog2);    
        
        List<Blog> allBlogs = dao.getAllBlogs();
        assertTrue(allBlogs.contains(blog1));
        assertTrue(allBlogs.contains(blog2));
        
        List<Blog> approvedBlogs = dao.getAllApprovedBlogs();
        assertTrue(approvedBlogs.contains(blog1));
        assertFalse(approvedBlogs.contains(blog2));
        
        List<Blog> unapprovedBlogs = dao.getAllUnapprovedBlogs();
        assertFalse(unapprovedBlogs.contains(blog1));
        assertTrue(unapprovedBlogs.contains(blog2));

        List<Blog> tag1Blogs = dao.getBlogsByHashtag("Tag 1");
        assertTrue(tag1Blogs.contains(blog1));
        assertFalse(tag1Blogs.contains(blog2));   

        List<Blog> tag2Blogs = dao.getBlogsByHashtag("Tag 2");
        assertTrue(tag2Blogs.contains(blog1));
        assertTrue(tag2Blogs.contains(blog2));
        
        Blog latestBlog = dao.getLatestBlog();
        assertTrue(latestBlog.equals(blog2));
        
        List<Blog> author1Blogs = dao.getBlogsByAuthor(author1.getUsername());
        List<Blog> author2Blogs = dao.getBlogsByAuthor(author2.getUsername());
        
        assertTrue(author1Blogs.contains(blog1));
        assertFalse(author1Blogs.contains(blog2));
        
        assertFalse(author2Blogs.contains(blog1));
        assertTrue(author2Blogs.contains(blog2));
        
        authorDao.deleteAuthorByUsername(author1.getUsername());
        authorDao.deleteAuthorByUsername(author2.getUsername());
        
    }

    @Test
    public void testGetBlogsByAuthorId() {
    }

    @Test
    public void testUpdateAndDeleteBlog() {
        Author author1 = new Author();
        author1.setUsername("TestUser1");
        author1.setDisplayName("Test Name 1");
        author1.setPass("TestPassword1");
        author1.setPermissions(false);
        author1 = authorDao.addAuthor(author1);         
        
        Blog blog1 = new Blog();
        // INSERT CODE TO SET AUTHOR LATER     
        blog1.setUser(author1.getUsername());        
        blog1.setApproved(true);
        blog1.setTitle("Test Title");
        blog1.setContent("Test Content");
        blog1.setPublishDate("2020-01-01 10:10:10");
        List<String> tags = Arrays.asList("Tag 1", "Tag 2");
        blog1.setHashtags(tags);        
        blog1 = dao.addBlog(blog1);  

        blog1.setApproved(false);
        blog1.setTitle("New Title");
        blog1.setContent("New Content");
        blog1.setPublishDate("2030-01-01 10:10:10");  
        List<String> newTags = Arrays.asList("Tag 2", "Tag 3");
        blog1.setHashtags(newTags);
        dao.updateBlog(blog1);
        
        Blog fromDao = dao.getBlogById(blog1.getId());
        assertTrue(fromDao.equals(blog1));
        assertFalse(fromDao.getApproved());
        assertTrue(fromDao.getTitle().equals("New Title"));
        assertTrue(fromDao.getContent().equals("New Content"));
        assertTrue(fromDao.getPublishDate().equals("2030-01-01 10:10:10"));
        assertTrue(fromDao.getHashtags().contains("Tag 2"));
        assertTrue(fromDao.getHashtags().contains("Tag 3"));  
        assertFalse(fromDao.getHashtags().contains("Tag 1"));
        
        dao.deleteBlogById(blog1.getId());
        
        List<Blog> allBlogs = dao.getAllBlogs();
        assertTrue(allBlogs.isEmpty());
        authorDao.deleteAuthorByUsername(author1.getUsername());        
    }
    
}
