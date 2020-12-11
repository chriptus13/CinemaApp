package pt.isel.ls.model.commandResults;

import pt.isel.ls.model.CommandResult;

/**
 * Implementation for a single command result
 *
 * @param <E> Type of command result
 */
public class SingleCommandResult<E> implements CommandResult {
    private E value;

    public SingleCommandResult(E value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public Object getDefault() {
        return value;
    }
}
