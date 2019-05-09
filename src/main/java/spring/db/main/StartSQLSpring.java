package spring.db.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.db.dao.iml.MySqlDAO;
import spring.db.dao.objects.MP3;

public class StartSQLSpring {

    public static void main(String[] args) {
        MP3 mp3 = new MP3();
        mp3.setName("Song name");
        mp3.setAuthor("Song Author");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MySqlDAO mySqlDAO = context.getBean ("MySqlDAO", MySqlDAO.class);
        mySqlDAO.insert(mp3);
    }
}
