package spring.aop.loggins;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
@Aspect
public class MyLogger {

//    @Pointcut("execution(* *(..)) && within (spring.aop.objects.*)")
    @Pointcut("execution(* spring.aop.objects.Manager.*(..))")
    private void allMethods() {
    };


    @Around("allMethods()")
    public Object watchTime(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        System.out.println("method begin: " + joinPoint.getSignature().toShortString());
        Object output = null;

        for (Object object : joinPoint.getArgs()) {
            System.out.println("Param : " + object);
        }

        try {
            output = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("method ends: " + joinPoint.getSignature().toShortString()+", time  = " + time + " ms");

        return output;

    }


    @AfterReturning(pointcut = "allMethods()", returning = "object")
    public void print (Object object) {
        System.out.println("Print info begin >>");

        if (object instanceof Set) {
            Set set = (Set) object;
            for (Object o : set) {
                System.out.println(object);
            }


        } else if (object instanceof Map) {
            Map map = (Map) object;
            for (Object oject : map.keySet()) {
                System.out.println("key=" + object + ", " + map.get(object));
            }
        }
        System.out.println("Print info end  <<");
        System.out.println();

    }

}
