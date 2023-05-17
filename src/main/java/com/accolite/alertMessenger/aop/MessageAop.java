package com.accolite.alertMessenger.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessageAop {

    @Before(value = "execution(* com.accolite.alertMessenger.service.MessageService.*(..))")
    public void AopForMessage(){
        System.out.println("AOP executed");
    }

}
