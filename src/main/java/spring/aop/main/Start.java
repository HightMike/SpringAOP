package spring.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.aop.objects.FileManager;
import spring.aop.objects.SomeService;

import java.io.File;

public class Start {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        FileManager fileManager = applicationContext.getBean("fileManager", FileManager.class);
        fileManager.getExtensionCount("/home");
        fileManager.getExtensionCount("/home/mike");
    }
}
