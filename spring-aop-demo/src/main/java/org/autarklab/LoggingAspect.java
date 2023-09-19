package org.autarklab;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* org.autarklab.ShoppingCart.checkout(..))")
    public void beforeLogger(JoinPoint jp) {
        List<Object> listas = new ArrayList<>();
        listas.add(jp.getSignature());
        listas.add(jp.getArgs()[0].toString());
        System.out.println(listas);
        System.out.println("Loggers before");
    }

    @After("execution(* *.*.*.checkout(..))")
    public void afterLogger() {
        System.out.println("Logger afters");
    }

    @Pointcut("execution(* org.autarklab.ShoppingCart.quantity())")
    public void afterReturningPointCut(){

    }

    @AfterReturning(pointcut = "afterReturningPointCut()",
        returning = "retVal")
    public void afterReturning(String retVal){
        System.out.println("After returning " + retVal);
    }
}
