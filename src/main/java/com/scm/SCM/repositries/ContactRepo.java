package com.scm.SCM.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scm.SCM.entities.Contact;
import com.scm.SCM.entities.User;


public interface ContactRepo extends JpaRepository<Contact, String>{
    //find the contact by users
    List<Contact> findByUser(User user);


    //custom query method
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(String userId);
}
