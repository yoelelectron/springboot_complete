package org.autarklab;

import org.springframework.stereotype.Component;

@Component
public class StretcherBearer implements Staff{

    public void assist(){
        System.out.println("Assisting Doctor and Nurses...");
    }
}
