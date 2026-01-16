package com.scm.SCM.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.SCM.services.UserServices;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userService ; 

    

    //user dashboard page
    @RequestMapping(value="/dashboard")
    public String userDashborad() {
        return "user/dashboard";
    }
    
    //user add contact page
    @RequestMapping(value="/addContact", method=RequestMethod.GET)
    public String userAddContact() {
        return "user/addContact";
    }

    //user profile page
    @RequestMapping(value="/profile")
    public String userProfile(Model model ,Authentication authentication) {
       
        return "user/profile";
    }


    //user view contact page
    @RequestMapping(value="/viewContact", method=RequestMethod.GET)
    public String userViewContact() {
        return "user/viewContact";
    }


    //user edit contact page
    @RequestMapping(value="/editContact", method=RequestMethod.GET)
    public String userEditContact() {
        return "user/editContact";
    }

    

    //user delete contact page
    @RequestMapping(value="/deleteContact", method=RequestMethod.GET)
    public String userDeleteContact() {
        return "user/deleteContact";
    }


    //user search contact page
    @RequestMapping(value="/searchContact", method=RequestMethod.GET)
    public String userSearchContact() {
        return "user/searchContact";
    }


}
