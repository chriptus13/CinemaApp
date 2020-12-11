package pt.isel.ls.model.exceptions;

public class InvalidRowException extends CommandExecutionException {

    public InvalidRowException() {
        super("Row doesn't exists in theater");
    }
}
