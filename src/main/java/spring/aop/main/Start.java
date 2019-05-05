package spring.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.aop.objects.FileManager;
import spring.aop.objects.FileManager2;
import spring.aop.objects.SomeService;

import java.io.File;

public class Start {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        FileManager fileManager = (FileManager) applicationContext.getBean("fileManager");
        FileManager2 fileManager2 = (FileManager2) applicationContext.getBean("fileManager2");

        fileManager.getExtensionCount("/home/mike/файлы");
        fileManager.getExtensionCount("/home/mike/Documents");
        fileManager.getExtensionList("/home/mike/Documents");
        fileManager2.getExtensionCount("/home/mike/Documents");




    }
}
