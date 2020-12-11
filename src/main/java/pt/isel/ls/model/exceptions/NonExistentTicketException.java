package pt.isel.ls.model.exceptions;

public class NonExistentTicketException extends CommandExecutionException {

    public NonExistentTicketException() {
        super("Ticket doesn't exists");
    }
}
