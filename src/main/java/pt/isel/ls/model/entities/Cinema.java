package pt.isel.ls.model.entities;

/**
 * Class that represents a Cinema in the repository
 */
public class Cinema {
    public final int id;
    public final String name, city;

    public Cinema(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Cinema(String name, String city) {
        this(-1, name, city);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Cinema && id == ((Cinema) obj).id;
    }

    @Override
    public String toString() {
        return id + ": " + name + '(' + city + ')';
    }
}
