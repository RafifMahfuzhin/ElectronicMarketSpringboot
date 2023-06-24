package com.controller;

import com.dto.UserDto;
import com.entity.User;
import com.repository.RoleRepository;
import com.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.entity.Role;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private RoleRepository roleRepository;

    public AuthController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("user", userDto);
            redirectAttributes.addFlashAttribute("errorMessage", "Email Sudah Terpakai");
            return "redirect:/register?error";
        }

        userService.saveUser(userDto);
        redirectAttributes.addFlashAttribute("successMessage", "Registrasi Berhasil");
        return "redirect:/register?success";
    }


    @GetMapping("/users")
    public String getUserList(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "users :: content";
    }
    
    @GetMapping("/users/{email}/delete")
    public String deleteUser(@PathVariable("email") String email) {
        userService.deleteUserByEmail(email);
        return "redirect:/index";
    }


    @PostMapping("/users/{email}/update")
    public String updateUser(@PathVariable("email") String email, @ModelAttribute("user") UserDto updatedUserDto) {
        userService.updateUserByEmail(email, updatedUserDto);
        return "redirect:/index"; 
    }
}
