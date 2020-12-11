package pt.isel.ls.model.exceptions;

/**
 * Exception thrown when occurs any problem during the setup of any command
 */
public class CommandException extends Exception {
    public CommandException(String msg) {
        super(msg);
    }
}
