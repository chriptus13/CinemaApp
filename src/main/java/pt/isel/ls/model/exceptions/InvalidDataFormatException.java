package pt.isel.ls.model.exceptions;

public class InvalidDataFormatException extends ParameterException {
    public InvalidDataFormatException() {
        super("Not sufficient parameters or wrong format(try YYYY-MM-DD HH-MM)");
    }
}
