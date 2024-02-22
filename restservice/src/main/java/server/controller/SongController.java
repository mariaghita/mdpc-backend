package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.Song;
import server.song.SongRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/server/songs")
public class SongController {
    @Autowired
    private SongRepository songRepository;

    @RequestMapping(value = "/filter/artist",method = RequestMethod.GET)
    public List<Song> getAllSongsForArtist(@RequestParam("artist") String artist) {
        return songRepository.getSongsFromArtist(artist);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Song> getAllSongs() {
        return (List<Song>) songRepository.getAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Song create(@RequestBody Song song){
        System.out.println("alo.");
        return songRepository.add(song);
    }
}
