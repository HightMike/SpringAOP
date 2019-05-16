package spring.db.dao.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.db.dao.interfaces.MP3Dao;
import spring.db.dao.objects.MP3;

import javax.sql.DataSource;
import java.util.List;


@Component("MySqlDAO")
public class MySqlDAO implements MP3Dao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void insert(MP3 mp3) {
        String sql = "insert into MP3 (author, name) value (?,?);";
        jdbcTemplate.update(sql, mp3.getName(), mp3.getAuthor());

    }

    @Override
    public void delete(MP3 mp3) {

    }

    @Override
    public void deleteByID(int id) {
        String sql = "delete from MP3 where id=?;";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void addMP3List(List<MP3> list) {
        //String sql = "insert into MP3 (author, name) value (?,?);";

        for(MP3 mp3 : list) {

            //jdbcTemplate.update(sql, mp3.getName(), mp3.getAuthor());
            insert(mp3);

        }

    }

    @Override
    public MP3 getMP3ByID(int id) {
        return null;
    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        return null;
    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
        return null;
    }
}
