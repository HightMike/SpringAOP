package spring.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.aop.SomeService.SomeService;

public class Start {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        SomeService someService = applicationContext.getBean("someService", SomeService.class);
        double val = someService.getDoubleValue();
        someService.divide(5,0);
    }
}
