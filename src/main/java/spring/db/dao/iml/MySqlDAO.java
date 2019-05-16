package spring.db.dao.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import spring.db.dao.interfaces.MP3Dao;
import spring.db.dao.objects.MP3;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component("MySqlDAO")
public class MySqlDAO implements MP3Dao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void insert(MP3 mp3) {
        String sql = "insert into MP3 (author, name) value (:name, :author)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", mp3.getName());
        params.addValue("author", mp3.getAuthor());

        jdbcTemplate.update(sql, params);

    }

    @Override
    public void delete(MP3 mp3) {

    }

    @Override
    public void deleteByID(int id) {
        String sql = "delete from MP3 where id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);

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
        String sql = "select * from MP3 where id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper()); // передаем выборку сюда

    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        String sql = "select * from MP3 where upper(name) like :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "%" +name.toUpperCase() +"%");
        return jdbcTemplate.query(sql, params, new MP3RowMapper()); // передаем выборку сюда
    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
        String sql = "select * from MP3 where upper(name) like :author";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", "%" +author.toUpperCase() +"%");
        return jdbcTemplate.query(sql, params, new MP3RowMapper()); // передаем выборку сюда
    }

    private static final class MP3RowMapper implements RowMapper<MP3> {

        @Override
        public MP3 mapRow(ResultSet resultSet, int i) throws SQLException {
            MP3 mp3 = new MP3();
            mp3.setId(resultSet.getInt("id"));
            mp3.setName(resultSet.getString("name"));
            mp3.setAuthor(resultSet.getString("author"));
            return mp3;

        }
    }

}
