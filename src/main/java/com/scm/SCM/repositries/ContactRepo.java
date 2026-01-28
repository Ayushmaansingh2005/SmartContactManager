package com.scm.SCM.repositries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scm.SCM.entities.Contact;
import com.scm.SCM.entities.User;


public interface ContactRepo extends JpaRepository<Contact, String>{
    //find the contact by users
    Page<Contact> findByUser(User user ,Pageable pageable);


    //custom query method
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(String userId);

    Page<Contact> findByUserAndNameContaining(User user,String namekeyword,Pageable pageable);
    Page<Contact> findByUserAndEmailContaining(User user,String emailkeyword,Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining(User user,String phoneekeyword,Pageable pageable);

}
