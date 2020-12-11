package pt.isel.ls.model.exceptions;

public class NonExistentSessionException extends CommandExecutionException {
    public NonExistentSessionException() {
        super("Session doesn't exist");
    }
}
