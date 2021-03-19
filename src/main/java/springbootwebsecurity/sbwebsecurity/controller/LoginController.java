package springbootwebsecurity.sbwebsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springbootwebsecurity.sbwebsecurity.model.User;
import springbootwebsecurity.sbwebsecurity.security.UserDetailsServiceImp;
import springbootwebsecurity.sbwebsecurity.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

	private final UserDetailsServiceImp userDetailsServiceImp;
	private final UserService userService;

	@Autowired
	public LoginController(UserDetailsServiceImp userDetailsServiceImp, UserService userService) {
		this.userDetailsServiceImp = userDetailsServiceImp;
		this.userService = userService;
	}

	@GetMapping(value = "/user/{id}")
	public String helloUser(Model model, @PathVariable("id") Long id) {
		User user = userService.getUser(userDetailsServiceImp.getUser().getId());
		model.addAttribute("admin", userDetailsServiceImp.getUser());
		model.addAttribute("user", user);
		return "user/getUser";
	}


}