package pt.isel.ls.model.exceptions;

public class ExistentCinemaException extends CommandExecutionException {
    public ExistentCinemaException() {
        super("Cinema already registered");
    }
}
