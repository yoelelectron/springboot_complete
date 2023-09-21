package com.autarklab.springsecurityclient.event;

import com.autarklab.springsecurityclient.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationURL;

    public RegistrationCompleteEvent(User user, String applicationURL) {
        super(user);
        this.user = user;
        this.applicationURL = applicationURL;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setApplicationURL(String applicationURL) {
        this.applicationURL = applicationURL;
    }
}
