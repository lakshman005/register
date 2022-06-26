package com.revature.metro.user.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.metro.user.model.User;
import com.revature.metro.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public User registereduser(@RequestBody User details) throws Exception{
		details.setRole("user");
		String temp  = details.getEmailId();
		if(temp!=null && !"".equals(temp)) {
			User obj = service.getUser(temp);
			if(obj!=null) {
				throw new Exception ("ALready Exist");
			}
		}
		User obj = null;
		obj= service.register(details);
		return obj;
	}
	
	@PostMapping("/login")
	public User loginuser(@RequestBody User details)throws Exception {
		String email=details.getEmailId();
		String password= details.getPassword();
		User obj=null;
		if(email!=null && password!=null) {
			 obj =service.find(email, password);
		}
		if(obj==null) {
			throw new Exception("Check Password And Email");
		}
		return obj;
		
	}
	
	
		
}
