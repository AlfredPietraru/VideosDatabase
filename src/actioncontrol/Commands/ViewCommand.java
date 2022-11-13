package actioncontrol.Commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import org.json.simple.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public final class ViewCommand extends CommandType {
    public ViewCommand(final Input input, final JSONArray jsonArray,
                       final ActionInputData actionInputData) {
        super(input, jsonArray, actionInputData);
    }



    @Override
    public void execute() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", actionInputData.getActionId());
        UserInputData userInputData = checkUsernameExistence();
        if (userInputData == null) {
            jsonObject.put("message", "error -> The user " + actionInputData.getUsername()
                    + "does not exist in the database.");
        } else {
            Map<String, Integer> map = userInputData.getHistory();
            String movieName = actionInputData.getTitle();
            if (map.containsKey(movieName)) {
                map.put(movieName, map.get(movieName) + 1);
                jsonObject.put("message", "success -> " + movieName
                        + " was viewed with total views of " + map.get(movieName).toString());
            } else {
                map.put(movieName, 1);
                jsonObject.put("message", "success -> " + movieName
                        + " was viewed with total views of 1");
            }
        }
        this.jsonArray.add(jsonObject);
    }
}
