package SammyChan.BlogDataBaseTest.controller;

import SammyChan.BlogDataBaseTest.dao.AuthorDAO;
import SammyChan.BlogDataBaseTest.dao.BlogDAO;
import SammyChan.BlogDataBaseTest.model.Author;
import SammyChan.BlogDataBaseTest.model.Blog;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author sammychan
 */
@Controller
public class HashtagController {
    @Autowired
    BlogDAO blogDao;
    
    @Autowired
    AuthorDAO authorDao;
    
    /// Temporary Homepage DELETE LATER !!!!!!!!!!!!!!!!!!
    @GetMapping("/")
    public String testDisplayBlog(Model model){
        Blog blog = blogDao.getBlogById(1);
        model.addAttribute("blog", blog);
        return "index";
    }
    
    @GetMapping("hashtag")
    public String hashtagBlogs(String id, Model model){
        List<Blog> blogs = blogDao.getBlogsByHashtag(id);
        model.addAttribute("blogs", blogs);
        model.addAttribute("tag", id);
        return "hashtag";
    }  
    
    @GetMapping("pendingBlogs")
    public String getPendingBlogs(Model model){
        List<Blog> blogs = blogDao.getAllUnapprovedBlogs();
        model.addAttribute("blogs", blogs);
        return "pendingBlogs";
    }
    @GetMapping("deleteBlog")
    public String deleteBlog(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        blogDao.deleteBlogById(id);        
        return "redirect:/pendingBlogs";
    }
    @GetMapping("approveBlog")
    public String approveBlog(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Blog blog = blogDao.getBlogById(id);
        blog.setApproved(true);
        blogDao.updateBlog(blog);
                
        return "redirect:/pendingBlogs";
    }
    
    @GetMapping("authorDetail")
    public String authorDetail(String id, Model model){
        Author author = authorDao.getAuthor(id);
        List<Blog> blogs = blogDao.getBlogsByAuthor(id);
        model.addAttribute("author", author);
        model.addAttribute("blogs", blogs);
        return "authorDetail";
    }    
    
}
