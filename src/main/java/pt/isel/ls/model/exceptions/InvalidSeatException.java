package pt.isel.ls.model.exceptions;

public class InvalidSeatException extends CommandExecutionException {

    public InvalidSeatException() {
        super("Seat doesn't exists in theater");
    }
}
