package org.autarklab;

import org.springframework.stereotype.Component;

@Component
public class Doctor implements Staff{

    /*private String qualification;
    private String clinic;

    public Doctor(String clinic) {
        this.clinic = clinic;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }*/

    public void assist(){
        System.out.println("Assisting...");
    }
}
