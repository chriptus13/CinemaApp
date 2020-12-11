package pt.isel.ls.model.exceptions;

public class InvalidNumberOfParametersException extends ParameterException {

    public InvalidNumberOfParametersException() {
        super("Invalid number of parameters inserted");
    }
}
