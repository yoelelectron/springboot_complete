package com.autarklab.springsecurityclient.controller;

import com.autarklab.springsecurityclient.entity.User;
import com.autarklab.springsecurityclient.event.RegistrationCompleteEvent;
import com.autarklab.springsecurityclient.model.UserModel;
import com.autarklab.springsecurityclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class RegistrationController {

    private final UserService userService;
    private ApplicationEventPublisher publisher;

    @Autowired
    public RegistrationController(UserService userService,
                                  ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.publisher = publisher;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel,
                               final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        String validString = result.substring(0,5);

        System.out.println("validString = " + validString);

        if(validString.equals("valid")){
            String userDisplay = result.substring(6);
            System.out.println("userDisplay = " + userDisplay);
            return "User: " + userDisplay + " verifies Successfully";
        }

        return "Bad Credentials";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
