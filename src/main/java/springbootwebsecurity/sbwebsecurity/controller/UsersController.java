package springbootwebsecurity.sbwebsecurity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springbootwebsecurity.sbwebsecurity.model.Role;
import springbootwebsecurity.sbwebsecurity.model.User;
import springbootwebsecurity.sbwebsecurity.security.UserDetailsServiceImp;
import springbootwebsecurity.sbwebsecurity.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class UsersController {

    private final UserDetailsServiceImp userDetailsServiceImp;
    private final UserService userService;

    @Autowired
    public UsersController(UserDetailsServiceImp userDetailsServiceImp, UserService userService) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(@ModelAttribute("user") User user,
                              Model model) {
        model.addAttribute("admin", userDetailsServiceImp.getUser());
        model.addAttribute("ListUsers", userService.getAllUsers());
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin/index";
    }

    @GetMapping("/new")
    public String add(@ModelAttribute("user") User user, Model model) {
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/admin/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        List<Role> allRoles = userService.getRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin/edit";
    }


    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin/";
    }

    @PostMapping("{id}")
    public String update(@ModelAttribute("user") User user,@PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
