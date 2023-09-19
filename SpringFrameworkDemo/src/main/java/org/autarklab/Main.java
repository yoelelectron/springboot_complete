package org.autarklab;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(BeanConfig.class);

        Doctor doctor = context.getBean(Doctor.class);
        doctor.assist();
        doctor.setQualification("Urgency");
        System.out.println(doctor);

        /*
            ** singleton: will assign the qualification to both Doctor instances (doctor and doctor 1)
            ** prototype: will exclude the qualification to both Doctor instances (doctor and doctor 1 [null])
         */
        Doctor doctor1 = context.getBean(Doctor.class);
        System.out.println(doctor1);


        Nurse nurse = context.getBean(Nurse.class);
        nurse.assist();

        Staff staff = context.getBean(StretcherBearer.class);
        staff.assist();

    }
}