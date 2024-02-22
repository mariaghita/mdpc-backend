package server.song.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import server.Song;
import server.song.SongRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultSongRepository implements SongRepository {
    private SessionFactory sessionFactory;
    public DefaultSongRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Song> getSongsFromArtist(String artist) {
        List<Song> songs = new ArrayList<Song>();
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                songs = session.createQuery("from Song s where s.artist = :artist", Song.class)
                        .setParameter("artist", artist)
                        .list();
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction != null)
                    transaction.rollback();
            }
        }
        return songs;
    }

    @Override
    public List<Song> getSongsFromGenre(String genre) {
        List<Song> songs = new ArrayList<Song>();
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                songs = session.createQuery("from Song s where s.genre = :genre", Song.class)
                        .setParameter("genre", genre)
                        .list();
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction != null)
                    transaction.rollback();
            }
        }
        return songs;
    }

    @Override
    public Song getOne(Integer integer) {
        Song song = null;

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                song = session.get(Song.class, integer);
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction != null)
                    transaction.rollback();
            }
        }
        return song;
    }

    @Override
    public Iterable<Song> getAll() {
        List<Song> songs = new ArrayList<Song>();
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                songs = session.createQuery("from Song s order by s.artist desc", Song.class).list();
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction != null)
                    transaction.rollback();
            }
        }
        return songs;
    }

    @Override
    public Song add(Song song) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                session.save(song);
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction != null)
                    transaction.rollback();
            }
        }
        return song;
    }

    @Override
    public Song update(Song song) {
        return null;
    }
}
