package spring.aop.SomeService;


import org.springframework.stereotype.Component;

@Component
public class SomeService {


    public int getValue() {
        return 5;
    }
    public double getDoubleValue() {
        return 5.6;
    }

    public int divide(int a, int b) {

        int c = a/b;
        return c;

    }
}
