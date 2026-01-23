package com.scm.SCM.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message="Email is required")
    @Email(message="Invalid Email Address")
    private String email;

    @NotBlank(message="Phone number is Not blank")
    @Pattern(regexp="^[0-9]{10}$", message="Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message="Address Required")
    private String address;
    private String description;
    private boolean favourite;
    private String facebookLink;
    private String linkdinLink;

    private MultipartFile profileImage;


}
