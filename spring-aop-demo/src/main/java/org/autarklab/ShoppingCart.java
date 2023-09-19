package org.autarklab;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {

    public void checkout(String status) {

        /*
            The following are Separated Aspects
        */

        // logging
        // Authenticate & Authorize
        // Sanitize the data

        System.out.println("Checkout method....");
    }

    public int quantity(){
        return 2;
    }
}
