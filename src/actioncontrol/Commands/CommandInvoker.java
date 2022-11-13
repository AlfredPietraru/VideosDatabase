package actioncontrol.Commands;

import fileio.ActionInputData;
import fileio.Input;
import  org.json.simple.JSONArray;

public final class CommandInvoker {
    private final Input input;
    private ActionInputData actionInputData = null;
    private CommandFactory commandFactory = null;
    private final JSONArray jsonArray;

    public CommandInvoker(final Input input, final JSONArray jsonArray) {
        this.input = input;
        this.jsonArray = jsonArray;
    }

    public void setActionInputData(ActionInputData actionInputData) {

        this.actionInputData = actionInputData;
    }

    public void execute() {
        if (this.commandFactory == null) {
            this.commandFactory = new CommandFactory(this.input,
                    this.jsonArray, this.actionInputData);
        } else {
            commandFactory.setActionInputData(this.actionInputData);
        }
        CommandType commandType = this.commandFactory.create();
        if (commandType == null) {
            System.out.println("check the typing of the command");
            return;
        }
            commandType.execute();
    }

}
