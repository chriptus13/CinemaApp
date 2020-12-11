package pt.isel.ls.view;

import pt.isel.ls.model.CommandResult;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class CommandView {
    public abstract String getStringToPrint(CommandResult commandResult);

    public void show(CommandResult commandResult, String out) {
        String str = getStringToPrint(commandResult);
        if (out == null) System.out.println(str);
        else
            try (PrintWriter pw = new PrintWriter(out)) {
                pw.print(str);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }
}
