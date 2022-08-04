package com.sg.capstone.dao;

import com.sg.capstone.model.Blog;
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
//     List<Blog> getBlogsByAuthorId(int id);
    List<Blog> getBlogsByAuthor(String user);	
    List<Blog> getBlogsByHashtag(String tag);
    List<String> getAllTags();    
	
    Blog getLatestBlog();
    
    Blog addBlog(Blog blog);
    void updateBlog(Blog blog);
    void deleteBlogById(int id);
    
}
