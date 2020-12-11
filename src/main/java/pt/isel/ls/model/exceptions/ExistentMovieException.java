package pt.isel.ls.model.exceptions;

public class ExistentMovieException extends CommandExecutionException {
    public ExistentMovieException() {
        super("Movie already registered");
    }
}
