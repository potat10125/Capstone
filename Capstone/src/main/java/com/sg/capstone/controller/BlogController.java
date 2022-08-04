package com.sg.capstone.controller;

import com.sg.capstone.dao.AuthorDAO;
import com.sg.capstone.dao.BlogDAO;
import com.sg.capstone.dao.HashtagDao;
import com.sg.capstone.model.Blog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class BlogController {

	@Autowired
	BlogDAO blogDao;
	@Autowired 
	AuthorDAO authorDao;
	
	@GetMapping("/createBlog")
    public String displayBlogCreator(Model model) {
        return "createBlog";
    }
	@PostMapping("/addBlog")
    public String addBlog(String str, HttpServletRequest request) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String tags = request.getParameter("hashtags");
		String author = request.getParameter("author");
		if(authorDao.getAuthor(author)!=null){
			Blog blog = new Blog();
			blog.setApproved(authorDao.getAuthor(author).getPermissions());
			blog.setUser(author);
			blog.setContent(content);
			blog.setTitle(title);
			blog.setHashtags(Arrays.asList(tags.split("#")));
			blogDao.addBlog(blog);
		}
		//System.out.println(title+content+tags+author);
        return "redirect:/createBlog";
    }
	@GetMapping("/Blogs")
    public String displayBlogs(Model model) {
		List blogs = blogDao.getAllApprovedBlogs();
		model.addAttribute("blogs", blogs);
        return "Blogs";
    }


	@GetMapping("/viewBlog")
	public String displayBlog1(Model model){
		Blog blog = new Blog();
		blog.setUser("author");
		blog.setContent("content");
		ArrayList<String> a= new ArrayList();
		a.add("a");
		a.add("b");
		blog.setHashtags(a);
		blog.setPublishDate("today");
		blog.setTitle("title");
		model.addAttribute("blog", blog);
        return "viewBlog";
	}
	@GetMapping("/viewBlog/{blogId}")
    public String displayBlog(@PathVariable("blogId") int blogId, Model model){
		Blog blog = blogDao.getBlogById(blogId);
		model.addAttribute("blog", blog);
        return "viewBlog";
    }
	
	@GetMapping("/Home")
    public String displayBlog(Model model){
		Blog blog = blogDao.getLatestBlog();
		model.addAttribute("blog", blog);
        return "Home";
    }
    
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

}
