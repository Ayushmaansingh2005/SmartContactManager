package com.scm.SCM.entities;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Contact {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String picture;
    @Column(length=1000)
    private String description;
    private Boolean favourite=false;
    private String websiteLink;
    private String linkedInLink;
    //priavte List<String> socialLinks = new ArrayList<>();
    @ManyToOne
    private User user;
    @OneToMany(mappedBy="contact",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Sociallink> links =  new ArrayList<>();

}
