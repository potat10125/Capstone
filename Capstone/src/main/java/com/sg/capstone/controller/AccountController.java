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
	


}
