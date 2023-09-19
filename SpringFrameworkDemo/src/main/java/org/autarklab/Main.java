package org.autarklab;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");

        Doctor doctor = context.getBean(Doctor.class);
        doctor.assist();
        System.out.println(doctor.getQualification());
        System.out.println("Doctor assists in: ");
        System.out.println(doctor.getClinic());

        Nurse nurse = (Nurse)context.getBean("nurse");
        nurse.assist();

        Staff staff = (StretcherBearer) context.getBean("sb");
        staff.assist();

    }
}