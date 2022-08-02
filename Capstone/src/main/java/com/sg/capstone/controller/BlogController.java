package com.sg.capstone.controller;

import com.sg.capstone.dao.AccountDao;
import com.sg.capstone.dao.BlogDao;
import com.sg.capstone.dao.HashtagDao;
import com.sg.capstone.model.Blog;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;


@Controller
public class BlogController {
//	@Autowired
//	HashtagDao hashtagDao;
//	@Autowired
//	BlogDao blogDao;
//	@Autowired 
//	AccountDao accountDao;
	
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
//		if(accountDao.hasPermissions(author)){
//			hashtagDao.add(tags);
//			blogDao.addBlog(author,title, content, hashtagDao.getTags(tags));
//		}
		System.out.println(title+content+tags+author);
        return "redirect:/createBlog";
    }
	@GetMapping("/Blogs")
    public String displayBlogs(Model model) {
		//List blogs = blogDao.getAll();
		List<Blog> blogs = new ArrayList<Blog>();
		Blog blog = new Blog();
		blog.id=1;
		blog.title = "asdf";
		blogs.add(blog);	
		model.addAttribute("blogs", blogs);
        return "Blogs";
    }


	@GetMapping("/viewBlog")
	public String displayBlog1(Model model){
		Blog blog = new Blog();
		blog.content = "content";
		blog.title = "title";
		blog.date="some date";
		blog.hashtags="#hashtag";
		blog.author="author";
		
		model.addAttribute("blog", blog);
        return "viewBlog";
	}
	@GetMapping("/viewBlog/{blogId}")
    public String displayBlog(@PathVariable("blogId") int blogId, Model model){
//		Blog blog = blogDao.get(id);
		Blog blog = new Blog();
		blog.content = "content";
		blog.title = "title";
		blog.date="some date";
		blog.hashtags="#hashtag";
		blog.author="author";
		
		model.addAttribute("blog", blog);
        return "viewBlog";
    }

}
