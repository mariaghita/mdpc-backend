package server.song;

import server.Repository;
import server.Song;

import java.util.List;

public interface SongRepository extends Repository<Integer, Song> {
    List<Song> getSongsFromArtist(String artist);
    List<Song> getSongsFromGenre(String genre);
}
