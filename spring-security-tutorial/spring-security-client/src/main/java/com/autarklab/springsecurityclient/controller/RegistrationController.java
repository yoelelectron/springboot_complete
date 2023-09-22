package com.autarklab.springsecurityclient.controller;

import antlr.Token;
import com.autarklab.springsecurityclient.entity.User;
import com.autarklab.springsecurityclient.entity.VerificationToken;
import com.autarklab.springsecurityclient.event.RegistrationCompleteEvent;
import com.autarklab.springsecurityclient.model.PasswordModel;
import com.autarklab.springsecurityclient.model.UserModel;
import com.autarklab.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel,
                                HttpServletRequest request) {
        User user = userService.findUserByEmail(passwordModel.getEmail());

        String url = "";

        if(Objects.nonNull(user)){
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user,token);
            url = passwordResetTokenEmail(user, applicationUrl(request), token);
        }

        return url;
    }

    @PostMapping("savePassword")
    public String savePassword(@RequestParam(name = "token") String token,
                               @RequestBody PasswordModel passwordModel){

        String result = userService.validatePasswordResetToken(token);

        if(result.equalsIgnoreCase("valid")){
            return "Invalid Token";
        }

        Optional<User> user = userService.getUserByPasswordResetToken(token);

        if(user.isPresent()) {
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            return "Password reset successfully";
        } else {
            return "Failed!!!";
        }
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if(!userService.checkIfValidOldPassword(user,passwordModel.getOldPassword())) {
            return "Invalid old Password";
        }

        // Save new Password funtionality
        userService.changePassword(user, passwordModel.getNewPassword());
        return "Password has been changed successfully";
    }

    private String passwordResetTokenEmail(User user, String applicationUrl, String token) {
        String url = applicationUrl
                + "/user/savePassword?token="
                +token;

        //Send verification Email
        log.info("Click to reset your password: {}", url);

        return url;
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
