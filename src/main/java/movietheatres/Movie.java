package movietheatres;

import java.time.LocalTime;
import java.util.Objects;

public class Movie implements Comparable<Movie> {
    private String title;
    private LocalTime startTime;

    public Movie(String title, LocalTime startTime) {
        this.title = title;
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getTitle(), movie.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public int compareTo(Movie o) {
        return this.getStartTime().compareTo(o.getStartTime());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
