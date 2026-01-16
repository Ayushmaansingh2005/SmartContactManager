package com.scm.SCM.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication){

       //AuthenticationPrincipal principal = (AuthenticationPrincipal) authentication.getPrincipal(); 
        // if login is done with email id and password : how to find email
        if(authentication instanceof OAuth2AuthenticationToken){

            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String username="";

                if(clientId.equalsIgnoreCase("google")){
                    //if signin with github then how to find email
                    
                    username = oauth2User.getAttribute("email").toString();
                    System.out.println("getting email from google"+ username);

                }    
                else if(clientId.equalsIgnoreCase("github")){
                    //if sign in with google then how to find email id

                    username =  oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString()+ "@github.com";
                      System.out.print("getting email from github" + username);


                }  
                
                return username;

        }else{
            System.out.print("getting email from normal login");
            return authentication.getName();
        }
            
    }

}
