package actioncontrol.Commands;

import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONArray;

public class CommandFactory {
    private final Input input;
    private final JSONArray jsonArray;
    private ActionInputData actionInputData;

    public CommandFactory(Input input, JSONArray jsonArray, ActionInputData actionInputData) {
        this.input = input;
        this.jsonArray = jsonArray;
        this.actionInputData = actionInputData;
    }

    public void setActionInputData(ActionInputData actionInputData) {
        this.actionInputData = actionInputData;
    }

    public CommandType create() {
        if (actionInputData.getType().equals("favorite")) {
            return new FavoriteCommand(input, jsonArray, actionInputData);
        }
        if (actionInputData.getType().equals("view")) {
            return new ViewCommand(input, jsonArray, actionInputData);
        }
        if (actionInputData.getType().equals("rating")) {
            return new RatingCommand(input, jsonArray, actionInputData);
        }
        return null;
    }
}

