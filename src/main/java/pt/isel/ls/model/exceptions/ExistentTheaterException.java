package pt.isel.ls.model.exceptions;

public class ExistentTheaterException extends CommandExecutionException {
    public ExistentTheaterException() {
        super("Theater already registered");
    }
}
