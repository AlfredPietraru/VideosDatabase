package actioncontrol.Commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import java.util.Map;

public final class FavoriteCommand extends CommandType {
    public FavoriteCommand(final Input input,
                           final JSONArray jsonArray, final ActionInputData actionInputData) {
        super(input, jsonArray, actionInputData);
    }

    @Override
    public void execute() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.actionInputData.getActionId());
        UserInputData userInputData = checkUsernameExistence();

    if (userInputData == null) {
        jsonObject.put("message", "error -> The user " + actionInputData.getUsername()
                + "does not exist in the database.");
    } else {
        Map<String, Integer> map = userInputData.getHistory();
        if (map.containsKey(actionInputData.getTitle())) {
            String nameOfMovie = checkMovieNameInFavorites(userInputData);
            if (nameOfMovie != null) {
                jsonObject.put("message", "error -> " + actionInputData.getTitle()
                       + " is already in favourite list");
            } else {
                userInputData.getFavoriteMovies().add(actionInputData.getTitle());
                jsonObject.put("message", "success -> " + actionInputData.getTitle()
                        + " was added as favourite");
            }
        } else {
            jsonObject.put("message", "error -> " + actionInputData.getTitle()
                   + " is not seen");
        }
    }
        this.jsonArray.add(jsonObject);
    }
}
