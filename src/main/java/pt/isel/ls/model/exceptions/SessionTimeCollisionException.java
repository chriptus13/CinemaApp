package pt.isel.ls.model.exceptions;

public class SessionTimeCollisionException extends CommandExecutionException{
    public SessionTimeCollisionException() {
        super("There's a Session already marked for that theater at the given time slot");
    }
}
