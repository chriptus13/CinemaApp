package pt.isel.ls.model.exceptions;

public class NonExistentCinemaException extends CommandExecutionException {
    public NonExistentCinemaException(){
        super("Cinema doesn't exist");
    }
}
