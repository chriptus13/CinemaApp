package pt.isel.ls.model.exceptions;

public class NonExistentTheaterException extends CommandExecutionException {
    public NonExistentTheaterException() {
        super("Theater doesn't exist");
    }
}
