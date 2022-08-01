package com.sg.capstone.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BlogController {

	@GetMapping("createBlog")
    public String displayBlog(Model model) {
        return "createBlog";
    }
	@PostMapping("createBlog")
    public String createBlog(String str, HttpServletRequest request) {
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("blog"));
		System.out.println(request.getParameter("hashtags"));
        //List<Hashtags> hashtags = hashtagDao.getAll();
       //model.addAttribute("hashtags", hashtags);
        return "createBlog";
    }
   
}
