package com.autarklab.springsecurityclient.controller;

import antlr.Token;
import com.autarklab.springsecurityclient.entity.User;
import com.autarklab.springsecurityclient.entity.VerificationToken;
import com.autarklab.springsecurityclient.event.RegistrationCompleteEvent;
import com.autarklab.springsecurityclient.model.UserModel;
import com.autarklab.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
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

    @GetMapping("/resendVerificationToken")
    public String reSendVerificationToken(@RequestParam(name = "token") String oldToken,
                                          HttpServletRequest request){
        VerificationToken verificationToken =
                userService.generateNewVerificationToken(oldToken);

        User user = verificationToken.getUser();
        reSendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "Verification Link send";
    }

    private void reSendVerificationTokenMail(User user, String applicationUrl, VerificationToken token) {
        String url = applicationUrl
                + "/user/verifyRegistration?token="
                +token.getToken();

        //Send verification Email
        log.info("Click to verify: {}", url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
