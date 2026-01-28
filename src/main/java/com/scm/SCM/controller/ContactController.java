package com.scm.SCM.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.SCM.entities.Contact;
import com.scm.SCM.entities.User;
import com.scm.SCM.forms.ContactForm;
import com.scm.SCM.forms.ContactSearchForm;
import com.scm.SCM.helpers.AppConstants;
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
    public String viewContacts(
        @RequestParam(value="page",defaultValue="0") int page,
        @RequestParam(value="size" , defaultValue=AppConstants.PAGE_SIZE+"") int size,
        @RequestParam(value="sortBy" , defaultValue="name") String sortBy,
        @RequestParam(value="direction" , defaultValue="asc") String direction,Authentication authentication , Model model){


        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userServices.getUserByEmail(username);

       Page<Contact> pageContacts =  contactService.getByUser(user,page,size,sortBy,direction);
    //   for (Contact c : contacts) {
    //         System.out.println("contacts: " + c);
    //     }
       model.addAttribute("pageContacts",pageContacts);
       model.addAttribute("pageSize",AppConstants.PAGE_SIZE);
       model.addAttribute("contactSearchForm",new ContactSearchForm());


       return "user/viewContact";
    }

    //search handler

    @RequestMapping("/search")
    public String searchHandler(
        @ModelAttribute ContactSearchForm contactSearchForm,
        @RequestParam(value="page",defaultValue="0") int page,
        @RequestParam(value="size" , defaultValue=AppConstants.PAGE_SIZE+"") int size,
        @RequestParam(value="sortBy" , defaultValue="name") String sortBy,
        @RequestParam(value="direction" , defaultValue="asc") String direction,Model model,
        Authentication authentication
    ){


        var user = userServices.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));

        String field = contactSearchForm.getField();
        String value=contactSearchForm.getValue();
        Page<Contact> pageContacts = null;  
        if(field.equalsIgnoreCase("name")){
            pageContacts =  contactService.searchByName(value, size, page, sortBy, direction,user);

        }

        else if(field.equalsIgnoreCase("email")){
            pageContacts =  contactService.searchByEmail(value, size, page, sortBy, direction,user);

        }
        
        else if(field.equalsIgnoreCase("phone")){
            pageContacts =  contactService.searchByPhoneNumber(value, size, page, sortBy, direction,user);

        }

        model.addAttribute("contactSearchForm" , contactSearchForm);
        model.addAttribute("pageSize",AppConstants.PAGE_SIZE);
        model.addAttribute("pageContacts",pageContacts);

        logger.info("pageContacts {}",pageContacts);
        logger.info("value {} ",value);
        return "user/searchContact";
    }
    
}
