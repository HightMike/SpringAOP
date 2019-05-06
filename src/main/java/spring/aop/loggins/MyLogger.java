package spring.aop.loggins;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import spring.aop.objects.Manager;

import java.util.Map;
import java.util.Set;

@Component
@Aspect
public class MyLogger {

//    @Pointcut("execution(* *(..)) && within (spring.aop.objects.*)")
    @Pointcut("execution(* spring.aop.objects.Manager.*(..)) )")
    private void allMethods() {
    };

    @Pointcut("execution(java.util.Map *(..)) )")
    private void allmap() {
    };
    @Pointcut("execution(java.util.Set *(..)) )")
    private void allset() {
    };



    @Around("allMethods() && allmap()")
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

    @SuppressWarnings("rawtypes")
    @AfterReturning (pointcut = "allset()", returning = "object")
    public void printMap (Object object) {
        System.out.println("Print info begin >>");


            Set set = (Set) object;
            for (Object o : set) {
                System.out.println(object);
            }

        System.out.println("Print info end  <<");
        System.out.println();

    }

    @SuppressWarnings("rawtypes")
    @AfterReturning (pointcut = "allmap()", returning = "object")
    public void printSet (Object object) {
        System.out.println("Print info begin >>");

            Map map = (Map) object;
            for (Object oject : map.keySet()) {
                System.out.println("key=" + object + ", " + map.get(object));
            }

        System.out.println("Print info end  <<");
        System.out.println();

    }

}
