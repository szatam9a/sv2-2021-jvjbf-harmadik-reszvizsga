package streams;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SongService {
    private List<Song> songs = new LinkedList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public SongService() {
    }

    public List<Song> getSongs() {
        return songs;
    }

    public Optional<Song> shortestSong() {
        return songs.stream().
                sorted(Comparator.comparing(Song::getLength)).
                findFirst();
    }

    public List<Song> findSongByTitle(String title) {
        return songs.stream().filter(e -> e.getTitle().equals(title)).toList();
    }

    public boolean isPerformerInSong(Song s, String performer) {
        return s.getPerformers().contains(performer);
    }

    public List<String> titlesBeforeDate(LocalDate beforeThis) {
        return songs.stream().filter(e -> e.getRelease().isBefore(beforeThis)).map(e -> e.getTitle()).toList();
    }
}
