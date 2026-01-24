package com.scm.SCM.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.SCM.entities.Contact;
import com.scm.SCM.entities.User;
import com.scm.SCM.forms.ContactForm;
import com.scm.SCM.helpers.Helper;
import com.scm.SCM.helpers.Message;
import com.scm.SCM.helpers.MessageType;
import com.scm.SCM.services.ContactService;
import com.scm.SCM.services.ImageService;
import com.scm.SCM.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ImageService imageService;
    
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserServices userServices;
    //add contact page
    @RequestMapping("/add")
    public String addContactView(Model model){

        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm",contactForm);

        return "user/addContact";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String saveContact( @Valid @ModelAttribute ContactForm contactform ,BindingResult result, Authentication authentication , HttpSession session){

        //process the form data

        //validation
        if(result.hasErrors()){
            session.setAttribute("message",Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build());
            return "user/addContact";
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userServices.getUserByEmail(username);
        //form->>contact form coversion

        //image processing
        logger.info("file information : {}",contactform.getContact_image().getOriginalFilename());

        String filename = UUID.randomUUID().toString();

        String fileURL = imageService.uploadImage(contactform.getContact_image(),filename);
        Contact contact = new Contact();

        contact.setName(contactform.getName());
        contact.setFavourite(contactform.isFavourite());
        contact.setEmail(contactform.getEmail());
        contact.setPhoneNumber(contactform.getPhoneNumber());
        contact.setAddress(contactform.getAddress());
        contact.setDescription(contactform.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactform.getLinkdinLink());
        contact.setWebsiteLink(contactform.getFacebookLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);


        contactService.save(contact);

        System.out.println("Contact Form : "+contactform);

        //meassage on view of successfull contact saved
        session.setAttribute("message", Message.builder()
                    .content("New contact added succesfully")
                    .type(MessageType.blue)
                    .build());
        


        return "redirect:/user/contacts/add";
    }


    //view contacts
    @RequestMapping
    public String viewContacts(Authentication authentication , Model model){
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userServices.getUserByEmail(username);

       List<Contact> contacts =  contactService.getByUser(user);
    //   for (Contact c : contacts) {
    //         System.out.println("contacts: " + c);
    //     }
       model.addAttribute("contacts",contacts);
       return "user/viewContact";
    }
    
}
