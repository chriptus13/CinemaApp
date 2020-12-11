package pt.isel.ls.model.exceptions;

public class ExistentTicketException extends CommandExecutionException {

    public ExistentTicketException() {
        super("Ticket already registered");
    }
}
