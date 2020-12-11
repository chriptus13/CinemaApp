package pt.isel.ls.model;

/**
 * Specifies the operation that any command result should have
 */
public interface CommandResult {
    /**
     * Gets the value of this command result
     *
     * @return value of this command result
     */
    Object getValue();

    /**
     * Gets the value of default result
     */
    Object getDefault();
}
