package pt.isel.ls.model.exceptions;

public class NonExistentMovieException extends CommandExecutionException {
    public NonExistentMovieException() {
        super("Movie doesn't exist");
    }
}
