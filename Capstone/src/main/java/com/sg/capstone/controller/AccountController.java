package com.sg.capstone.controller;

import com.sg.capstone.dao.AuthorDAO;
import com.sg.capstone.dao.BlogDAO;
import com.sg.capstone.dao.HashtagDao;
import com.sg.capstone.model.Author;
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
public class AccountController {

	@Autowired
	BlogDAO blogDao;
	@Autowired 
	AuthorDAO authorDao;
	
	@GetMapping("/login")
    public String loginScreen(Model model) {
		//model.addAttribute("username","asdf");
        return "login";
    }
	
	@PostMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		Author author = authorDao.getAuthor(username);
		if(author!=null && author.getPass().equals(password)){
			model.addAttribute("username",username);
		}
		return "/login";
	}
        
        @GetMapping("/createAccount")
        public String createAccountScreen(Model model){
            return "createAccount";
        }
        
        @PostMapping("/createAccount")
        public String createAccount(Model model, HttpServletRequest request){
            String username = request.getParameter("username");
            String displayName = request.getParameter("displayName");
            String password = request.getParameter("pw");
            String pwConf = request.getParameter("pwConf");
            if(password.equals(pwConf)){               
                Author author = new Author();
                author.setUsername(username);
                author.setDisplayName(displayName);
                author.setPass(password);
                
                authorDao.addAuthor(author);
                
                model.addAttribute("displayName", displayName);
            }
            return "redirect:/Blogs";
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
