package com.scm.SCM.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.scm.SCM.services.impl.SecurityCustomUserDetailService;


@Configuration
public class SecurityConfig {

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user1 = User.withUsername("Ayushmaan")
    //             .password("{noop}123")
    //             .roles("ADMIN", "USER")
    //             .build();

    //     UserDetails user2 = User.withUsername("Ayushmaan2")
    //             .password("{noop}123")
    //             .roles("ADMIN", "USER")
    //             .build();

    //     return new InMemoryUserDetailsManager(user1, user2);


    @Autowired
    private SecurityCustomUserDetailService userDetailsService;

    @Autowired
    private OauthAuthenticationSuccessHandler handler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    // configuration of authentication provider for Spring boot
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService); // no args

        // user details service
        //daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        // password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{

        //configuration


        //we configures the url which are public and which are private
        httpSecurity.authorizeHttpRequests(authorize->{
            // authorize.requestMatchers("/home","/singUp","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        //formdefault login
        //if we want to change any thing related to form login then we visit thi
        httpSecurity.formLogin(formLogin->{

            //

            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.defaultSuccessUrl("/user/profile", true);
            //formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
                
            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
                
            // });

            formLogin.failureHandler(authenticationFailureHandler);
        });


        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });


        //oauth configuration
        httpSecurity.oauth2Login(oauth->{
            oauth.loginPage("/login");
            oauth.successHandler(handler);
            
        });

        return httpSecurity.build();
    }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
     
}
