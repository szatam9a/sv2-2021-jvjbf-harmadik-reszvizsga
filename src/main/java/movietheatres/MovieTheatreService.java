package movietheatres;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

public class MovieTheatreService {
    private Map<String, List<Movie>> shows = new LinkedHashMap<>();

    public void readFromFile(Path path) {
        try (Scanner sc = new Scanner(path)) {
            while (sc.hasNext()) {

                manageTheMap(sc.nextLine());
            }
        } catch (IOException io) {
        }
        System.out.println(shows);
    }

    private void manageTheMap(String nextLine) {
        Scanner s = new Scanner(nextLine);
        s.useDelimiter("[-;]");
        String cinema = s.next();
        String movie = s.next();
        String startTime = s.next();
        if (!shows.containsKey(cinema)) {
            shows.put(cinema, new LinkedList<>());
        }
        shows.get(cinema).add(new Movie(movie, LocalTime.parse(startTime)));
        for (Map.Entry<String, List<Movie>> ac : shows.entrySet()) {
            Collections.sort(ac.getValue());
        }
    }

    public MovieTheatreService() {
    }

    public Map<String, List<Movie>> getShows() {
        return shows;
    }

    public List<String> findMovie(String title) {
        return shows.entrySet().
                stream().
                filter(e -> e.getValue().
                        contains(new Movie(title, LocalTime.now()))).
                map(e -> e.getKey()).
                toList();
    }

    public LocalTime findLatestShow(String title) {
        return shows.values().stream().flatMap(e -> e.stream()).filter(e -> e.getTitle().equals(title)).sorted(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            }
        }).map(e -> e.getStartTime()).findFirst().orElseThrow(() -> new IllegalArgumentException("no show"));
    }
}
