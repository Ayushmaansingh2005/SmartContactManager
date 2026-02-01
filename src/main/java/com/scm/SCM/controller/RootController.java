package com.scm.SCM.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.SCM.entities.User;
import com.scm.SCM.helpers.Helper;
import com.scm.SCM.services.UserServices;

@ControllerAdvice
public class RootController {


    private Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserServices userService ;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication){
        if (authentication == null) {
            return;
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        if (user != null) {
            // Only print/add to model if user was actually found
            logger.info("User details loaded for: {}", user.getEmail());
            model.addAttribute("loggedinUser", user);
        } else {
            logger.warn("User authenticated but not found in database: {}", username);
        }
    }
}
