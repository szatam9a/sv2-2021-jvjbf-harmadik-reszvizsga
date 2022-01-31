package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {
    private Map<String, List<Movie>> shows = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
    });

    public void readFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String next;
            String[] tmp;
            String movieName;
            while ((next = br.readLine()) != null) {
                tmp = next.split("-");
                movieName = tmp[0];
                tmp = tmp[1].split(";");
                validateMovie(movieName);
                putInMovie(movieName, tmp[0], tmp[1]);
            }

        } catch (IOException io) {
            throw new IllegalArgumentException("No file");
        }
    }

    private void putInMovie(String movie, String title, String StartTime) {
        List<Movie> toAdd;
        for (Map.Entry<String, List<Movie>> actual : shows.entrySet()) {
            if (actual.getKey().equals(movie)) {
                System.out.println(actual);
                toAdd = actual.getValue();
                toAdd.add(new Movie(title, LocalTime.parse(StartTime)));
                return;
            }
        }
    }

    private void validateMovie(String movie) {
        if (!shows.keySet().contains(movie)) {
            shows.put(movie, new LinkedList<>());
        }
    }

    public List<String> findMovie(String title) {
        return shows.entrySet().stream().filter(e -> e.getValue().contains(new Movie(title, LocalTime.now()))).map(e -> e.getKey()).toList();
    }

    public Map<String, List<Movie>> getShows() {
        return shows;
    }

    public LocalTime findLatestShow(String title) {
        List<Movie> movies;
        movies = shows.values().stream().flatMap(e -> e.stream()).filter(e -> e.getTitle().equals(title)).toList();
        LocalTime latestT = LocalTime.of(0,0);
        Movie latestM = movies.get(0) ;
        for (Movie m : movies) {
            if (m.getStartTime().isAfter(latestT)){
                latestM= m;
                latestT=m.getStartTime();
            }
        }
        return latestT;
        //return movies.stream().map(e -> e.getStartTime()).findFirst().orElseThrow(() -> new IllegalArgumentException("no movie"));
    }
}
