package pt.isel.ls.model.exceptions;

public class InvalidParametersException extends ParameterException {

    public InvalidParametersException() {
        super("Invalid type of parameters or a certain parameter not inserted");
    }
}
