package pt.isel.ls.model.commandResults;

import pt.isel.ls.model.CommandResult;

import java.util.Collection;

/**
 * Implementation for Multiple Command results
 *
 * @param <E> Type of command result
 */
public class MultipleCommandResult<E> implements CommandResult {
    private final Collection<E> collection;
    E base;

    public MultipleCommandResult(Collection<E> c, E base) {
        collection = c;
        this.base = base;
    }

    @Override
    public Object getValue() {
        return collection;
    }

    @Override
    public Object getDefault() {
        return base;
    }
}
