package pt.isel.ls.model.exceptions;

public class InvalidAcceptException extends CommandException {
    public InvalidAcceptException() {
        super("Invalid accept type");
    }
}
