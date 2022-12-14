package SammyChan.BlogDataBaseTest.dao;

import SammyChan.BlogDataBaseTest.model.Blog;
import java.util.List;

/**
 *
 * @author sammychan
 */
public interface BlogDAO {

    Blog getBlogById(int id);
    List<Blog> getAllBlogs();
    List<Blog> getAllApprovedBlogs();
    List<Blog> getAllUnapprovedBlogs();
    List<Blog> getBlogsByAuthor(String user);
    List<Blog> getBlogsByHashtag(String tag);
    Blog getLatestBlog();
    
    Blog addBlog(Blog blog);
    void updateBlog(Blog blog);
    void deleteBlogById(int id);
    
}
