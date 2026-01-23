package com.scm.SCM.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.SCM.entities.User;
import com.scm.SCM.forms.UserForm;
import com.scm.SCM.helpers.Message;
import com.scm.SCM.helpers.MessageType;
import com.scm.SCM.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;




@Controller
public class pageController {

    @GetMapping("/")
    public String getMethodName() {
       return  "redirect:/home";
    }
    

    @Autowired
    private UserServices userServices;

    @RequestMapping("/home")
    public String Home(){
        System.out.println("home page handler");
        return "home";
    }

    //about

    @RequestMapping("/about")
    public String About() {
        System.out.println("this is about page");
        return "about";
    }

    //services

    @RequestMapping("/services")
    public String Services() {
        System.out.println("This is services page");
        return "services";
    }
    
    //contact page
     @RequestMapping("/contact")
    public String Contact() {
        System.out.println("This is services page");
        return "contact";
    }

    //signup page
     @RequestMapping("/singUp")
    public String SingUp(Model model) {
        System.out.println("This is services page");
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);
        return "singUp";
    }

    //login page
     @RequestMapping("/login")
    public String Login() {
        System.out.println("This is services page");
        return "login";
    }
    

    //processing signup
    @RequestMapping(value = "/do-register",method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm , BindingResult bindingResult ,HttpSession session){
        System.out.println("Processing registration");
        //fetch from data
        //user form
        System.out.print(userForm);
        //validate from data
        if(bindingResult.hasErrors()){
            return "singUp";
        }


        //save to database
        //using user service
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://www.pexels.com/photo/person-holding-camera-1704488/")
        // .build();
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://www.pexels.com/photo/person-holding-camera-1704488/");
        User savedUser = userServices.saveUser(user);
        System.err.println("user saved");
        //message = "registration successfull"
        //redirect to login page

        //message notificaation
        Message message= Message.builder().content("Registration successfull").type(MessageType.blue).build();
        session.setAttribute("message",message);
        return"redirect:/singUp";
    }

    
}





