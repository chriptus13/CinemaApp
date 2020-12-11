package pt.isel.ls.model.entities;

/**
 * Class that represents a Theater in the repository
 */
public class Theater {
    public final int id, nRows, nSeatsPerRow, availableSeats;
    public final Cinema cinema;
    public final String name;

    public Theater(int id, Cinema cinema, String name, int nRows, int nSeatsPerRow, int availableSeats) {
        this.id = id;
        this.cinema = cinema;
        this.name = name;
        this.nRows = nRows;
        this.nSeatsPerRow = nSeatsPerRow;
        this.availableSeats = availableSeats;
    }

    public Theater(int id, int cinemaID, String name, int nRows, int nSeatsPerRow, int availableSeats) {
        this(id, new Cinema(cinemaID, null, null), name, nRows, nSeatsPerRow, availableSeats);
    }

    public Theater(int cinemaID, String name, int nRows, int nSeatsPerRow) {
        this(-1, new Cinema(cinemaID, null, null), name, nRows, nSeatsPerRow, nRows * nSeatsPerRow);
    }

    public Theater(int cinemaID, int theaterID) {
        this(theaterID, cinemaID, "", 0, 0, 0);
    }

    public int getId() {
        return id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public int getNRows() {
        return nRows;
    }

    public int getNSeatsPerRow() {
        return nSeatsPerRow;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Theater && cinema.getId() == ((Theater) obj).getCinema().getId() && id == ((Theater) obj).id;
    }

    @Override
    public String toString() {
        return id + ": " + name + "(Cinema: " + cinema.name + ", Rows: " + nRows + ", SeatsPerRow: " + nSeatsPerRow + ")";
    }
}
