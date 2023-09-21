package com.autarklab.springsecurityclient.event.listener;

import com.autarklab.springsecurityclient.entity.User;
import com.autarklab.springsecurityclient.event.RegistrationCompleteEvent;
import com.autarklab.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener
        implements ApplicationListener<RegistrationCompleteEvent> {

    private UserService userService;

    @Autowired
    public RegistrationCompleteEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        //Create the Verification Token for the User with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);

        // Send Email to User
        String url = event.getApplicationURL()
                        + "/user/verifyRegistration?token="
                        + token;

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}", url);
    }
}
