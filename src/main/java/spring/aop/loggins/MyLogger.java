package spring.aop.loggins;


import org.springframework.stereotype.Component;

@Component
public class MyLogger {

    public void printValue(Object object) {
        System.out.println(object);
    }

    public void init() {
        System.out.println("init");
    }

    public void close () {
        System.out.println("close");
    }

}
