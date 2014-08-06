package cz.jiripinkas.jba.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.exception.RssException;
import cz.jiripinkas.jba.repository.UserRepository;
import cz.jiripinkas.jba.service.BlogService;
import cz.jiripinkas.jba.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	
	
	
	
	
	@ModelAttribute("blog")
	public Blog constructBlog(){
		return new Blog();
	}
	
	
	
	
	
	@RequestMapping("/account")
	public String account(Model model,Principal principal){
		String userName=principal.getName();
		System.out.println("userName==>"+userName);
		model.addAttribute("user", userService.findOneWithBlogs(userName));
		return "account";
	}
	
	@RequestMapping(value="/account",method=RequestMethod.POST)
	public String doAddBlog(Model model,Principal principal,@Valid @ModelAttribute("blog") Blog blog,BindingResult result) throws RssException{
		if(result.hasErrors()){
			return account(model,principal);
		}
		String name=principal.getName();
		blogService.saveBlog(blog,name);
		//return "user-register";
		return "redirect:/account.html";
	}
	
	@RequestMapping(value="/blog/remove/{id}")
	public String removeBlog(@PathVariable int id){
		Blog blog =blogService.findOne(id);
		blogService.deleteBlog(blog);
		return "redirect:/account.html";
	}
	
	
	
	
}