package org.autarklab;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");

        Doctor doctor = context.getBean(Doctor.class);
        doctor.assist();
        /*doctor.setQualification("Urgency");
        doctor.setClinic("Santa Fe");
        System.out.println(doctor.getQualification());
        System.out.println("Doctor assists in: ");
        System.out.println(doctor.getClinic());*/

        Nurse nurse = context.getBean(Nurse.class);
        nurse.assist();

        Staff staff = context.getBean(StretcherBearer.class);
        staff.assist();

    }
}