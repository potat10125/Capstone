package com.sg.capstone.dao;

import com.sg.capstone.dao.AuthorDAODB.AuthorMapper;
import com.sg.capstone.model.Author;
import com.sg.capstone.model.Blog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sammychan
 */
@Repository
public class BlogDAODB implements BlogDAO{
    @Autowired
    JdbcTemplate jdbc;       

    @Override
    public Blog getBlogById(int id) {
        try{
            final String sql = "SELECT * FROM blog WHERE id = ?;";
            Blog blog =  jdbc.queryForObject(sql, new BlogMapper(), id);
            blog.setHashtags(getHashtagsForBlog(blog.getId()));
            blog.setUser(getAuthorForBlog(blog.getId()));
            //System.out.println(getAuthorForBlog(blog.getId()));
            return blog;
        }catch (DataAccessException e){
            return null;
        } 
    }
    
//    USE ONCE AUTHOR AUTHOR DAO is implmented    
    private String getAuthorForBlog(int id){
        try{
            final String sql = "SELECT a.* FROM authors a JOIN blog b ON a.username = b.author WHERE b.id = ?";
            Author author = jdbc.queryForObject(sql, new AuthorMapper(), id);
            return author.getUsername();
        }catch (DataAccessException e){
            return null;
        }        
    }
    
    private List<String> getHashtagsForBlog(int id){
        try{
            final String sql = "SELECT tag FROM hashtag WHERE blogId = ?";
            List<String> tags = jdbc.queryForList(sql, String.class, id);
            return tags;
        }catch (DataAccessException e){
            return Collections.emptyList();
        }         
    }

    @Override
    public List<Blog> getAllBlogs() {
        final String sql = "SELECT * FROM blog;";
        List<Blog> blogs = jdbc.query(sql, new BlogMapper());  
        for (Blog blog : blogs){
            blog.setHashtags(getHashtagsForBlog(blog.getId()));
        }
        return blogs;    
    }

    @Override
    public List<Blog> getAllApprovedBlogs() {
        final String sql = "SELECT * FROM blog WHERE approved = TRUE;";
        List<Blog> blogs = jdbc.query(sql, new BlogMapper());  
        for (Blog blog : blogs){
            blog.setHashtags(getHashtagsForBlog(blog.getId()));
        }
        return blogs;      
    }

    @Override
    public List<Blog> getAllUnapprovedBlogs() {
        final String sql = "SELECT * FROM blog WHERE approved = FALSE;";
        List<Blog> blogs = jdbc.query(sql, new BlogMapper());  
        for (Blog blog : blogs){
            blog.setHashtags(getHashtagsForBlog(blog.getId()));
        }
        return blogs;         
    }

    @Override
    public List<Blog> getBlogsByAuthorId(int id) {
        final String sql = "SELECT * FROM blog WHERE author = ?;";
        List<Blog> blogs = jdbc.query(sql, new BlogMapper(), id);  
        for (Blog blog : blogs){
            blog.setHashtags(getHashtagsForBlog(blog.getId()));
        }
        return blogs;     }

    @Override
    public List<Blog> getBlogsByHashtag(String tag) {
        final String sql = "SELECT b.* FROM blog b JOIN hashtag h ON b.id = h.blogId WHERE h.tag = ?;";
        List<Blog> blogs = jdbc.query(sql, new BlogMapper(), tag);  
        for (Blog blog : blogs){
            blog.setHashtags(getHashtagsForBlog(blog.getId()));
        }
        return blogs;
    }

    @Override
    public Blog getLatestBlog() {
            final String sql = "SELECT * FROM blog ORDER BY publish_date DESC LIMIT 1;";
            Blog blog =  jdbc.queryForObject(sql, new BlogMapper());
            blog.setHashtags(getHashtagsForBlog(blog.getId()));          
            return blog;            
    }
    
    private void updateHashtags(Blog blog){
        // Remove all hashtags incase some are no longer in the list
        final String remove = "DELETE FROM hashtag WHERE blogId = ?";
        jdbc.update(remove, blog.getId());
        
        // Readd hashtags and add new ones
        final String add = "INSERT IGNORE INTO hashtag(tag, blogId) VALUES (?, ?);";        
        for(String tag : blog.getHashtags()){
            jdbc.update(add, tag, blog.getId());
        }         
    }

    @Override    
    @Transactional
    public Blog addBlog(Blog blog) {          
        final String sql = "INSERT INTO blog(content, title, approved, author) VALUES (?, ?, ?, ?);";
        jdbc.update(sql, blog.getContent(), blog.getTitle(), 
                blog.getApproved(), blog.getUser());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        blog.setId(newId);
        updateHashtags(blog);    
        
        return blog; 
    }

    @Override
    public void updateBlog(Blog blog) {
        // MODIFY TO ACCOUNT FOR AUTHOR CODE TO SET AUTHOR LATER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!        
        final String sql = "UPDATE blog SET content = ?, title = ?, approved = ?, publish_date = ? WHERE id = ?;";
        jdbc.update(sql , blog.getContent(), blog.getTitle(), blog.getApproved(), blog.getPublishDate(), blog.getId());  
        updateHashtags(blog);  
    }

    @Override
    public void deleteBlogById(int id) {
        final String sql = "DELETE FROM blog WHERE id = ?;";
        jdbc.update(sql, id);    
    }
    
    public static final class BlogMapper implements RowMapper<Blog> {
        @Override
        public Blog mapRow(ResultSet rs, int index) throws SQLException{
            Blog blog  = new Blog();
            
            blog.setId(rs.getInt("id"));
            blog.setTitle(rs.getString("title"));
            blog.setContent(rs.getString("content"));
            blog.setApproved(rs.getBoolean("approved"));
            blog.setPublishDate(rs.getString("publish_Date"));                 
            return blog;
        }    
    }    
    
}
