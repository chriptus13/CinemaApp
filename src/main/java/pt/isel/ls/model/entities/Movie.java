package pt.isel.ls.model.entities;

/**
 * Class that represents a Movie in the repository
 */
public class Movie {
    public final int id, duration, releaseYear;
    public final String name;

    public Movie(String name, int releaseYear, int duration) {
        this(-1, name, releaseYear, duration);
    }

    public Movie(int id, String name, int releaseYear, int duration) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Movie && id == ((Movie) obj).id;
    }

    @Override
    public String toString() {
        return id + ": " + name + "(releaseYear: " + releaseYear + "| " + duration + "min)";
    }
}