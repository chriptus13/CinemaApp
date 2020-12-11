package pt.isel.ls.model.exceptions;

public class DatabaseErrorException extends CommandExecutionException {

    public DatabaseErrorException() {
        super("Error with database");
    }
}
