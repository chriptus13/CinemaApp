package pt.isel.ls.model.commandResults;

import pt.isel.ls.model.CommandResult;

public class ViewChangeCommandResult implements CommandResult {
    private int port;

    public ViewChangeCommandResult(int port) {
        this.port = port;
    }

    @Override
    public Object getValue() {
        return port;
    }

    @Override
    public Object getDefault() {
        return port;
    }
}
