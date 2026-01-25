package com.scm.SCM.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.SCM.entities.Contact;
import com.scm.SCM.entities.User;

public interface ContactService {


    //save contacts
    Contact save(Contact contact);

    //get contact 
    Contact update(Contact contact);

    //get contact
    List<Contact> getAll();

    //get contact by id 
    Contact getById(String id);

    //delete contact 
    void delete(String id);

    //search contact
    List<Contact> search(String name , String email , String phoneNumber);

    //get contact by user id
    List<Contact> getByUserId(String userId);

    //get by contacts user 
    Page<Contact> getByUser(User user,int page , int size,String sortField , String sortDirection );


}
