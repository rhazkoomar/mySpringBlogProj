package cz.jiripinkas.jba.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public User construct(){
		return new User();
	}
	
	@RequestMapping
	public String showRegister(){
		return "user-register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user,BindingResult result){
		if(result.hasErrors()){
			return"user-register";
		}
		
		userService.save(user);
		//return "user-register";
		return "redirect:/register.html?sucess=true";
	}
	
	@RequestMapping("/available")
	@ResponseBody
	public String available(@RequestParam String username){
		System.out.println("username==>"+username);
		System.out.println("userService.findOne(username)==>"+userService.findOne(username));
		Boolean available =userService.findOne(username)==null;
		System.out.println("available==>"+available);
		return available.toString();
		
	}

}
