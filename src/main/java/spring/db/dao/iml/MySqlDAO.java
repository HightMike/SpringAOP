package spring.db.dao.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import spring.db.dao.interfaces.MP3Dao;
import spring.db.dao.objects.MP3;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@Component("MySqlDAO")
public class MySqlDAO implements MP3Dao {

    private SimpleJdbcInsert insertmp3;

    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertmp3 = new SimpleJdbcInsert(dataSource).withTableName("MP3").usingColumns("name", "author");
    }


    @Override
    public int insert(MP3 mp3) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", mp3.getName());
        params.addValue("name", mp3.getAuthor());

        return insertmp3.execute(params);


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
    public int addMP3List(List<MP3> list) {
        String sql = "insert into MP3 (author, name) value (:name,:author)";
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
        int[] updateCounts = jdbcTemplate.batchUpdate(sql, batch);

        return updateCounts.length;

    }



    @Override
    public MP3 getMP3ByID(int id) {
        String sql = "select * from MP3 where id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper()); // передаем выборку сюда

    }

    @Override
    public Map<String, Integer> groupbyAuthor() {
        String sql = "SELECT author, count(name) as count from MP3 group by author";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<String, Integer> map =  new TreeMap<>();
                while (resultSet.next()){
                    String author = resultSet.getString("author");
                    int count = resultSet.getInt("count");
                    map.put(author,count);

                }
                return map;
            }
        });

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
