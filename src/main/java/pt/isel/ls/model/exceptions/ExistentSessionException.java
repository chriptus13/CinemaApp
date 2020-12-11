package pt.isel.ls.model.exceptions;

public class ExistentSessionException extends CommandExecutionException {
    public ExistentSessionException() {
        super("Session already registed");
    }
}
