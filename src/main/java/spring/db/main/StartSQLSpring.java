package spring.db.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.db.dao.iml.MySqlDAO;
import spring.db.dao.objects.MP3;

import java.util.ArrayList;
import java.util.List;

public class StartSQLSpring {

    public static void main(String[] args) {
        MP3 mp3 = new MP3();
        mp3.setName("Thunder");
        mp3.setAuthor("ImagineDragons");
        MP3 mp32 = new MP3();
        mp32.setName("Song name");
        mp32.setAuthor("Song Author");

        List<MP3> list = new ArrayList<>();
        list.add(mp3);
        list.add(mp32);

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        MySqlDAO mySqlDAO = context.getBean ("MySqlDAO", MySqlDAO.class);
        System.out.println(mySqlDAO.insert(mp3));

    }
}
