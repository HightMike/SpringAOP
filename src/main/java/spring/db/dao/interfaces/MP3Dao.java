package spring.db.dao.interfaces;

import spring.db.dao.objects.MP3;

import java.util.List;

public interface MP3Dao {

    void insert (MP3 mp3);

    void delete (MP3 mp3);

    void deleteByID (int id);

    void addMP3List (List<MP3> list);

    MP3 getMP3ByID (int id);

    List<MP3> getMP3ListByName(String name);

    List<MP3> getMP3ListByAuthor(String author);


}
