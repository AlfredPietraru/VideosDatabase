package actioncontrol.Commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.Map;

public final class RatingCommand extends CommandType {
    public RatingCommand(final Input input, final JSONArray jsonArray,
                         ActionInputData actionInputData) {
        super(input, jsonArray, actionInputData);
    }

    private void rateMovie(final JSONObject jsonObject) {
        List<ActionInputData> actionListOnSameMovie = this.input.getCommands().stream().
                filter(a -> this.actionInputData.getTitle().equals(a.getTitle())
                        && a != this.actionInputData).toList();
        if (actionListOnSameMovie.size() == 0) {
            String gradeString = String.valueOf(this.actionInputData.getGrade());
            jsonObject.put("message", "success -> " + this.actionInputData.getTitle()
                    + " was rated with " + gradeString + " by "
            + this.actionInputData.getUsername());
        } else {
            ActionInputData fatalAction = actionListOnSameMovie.stream()
                    .filter(a -> a.getActionType().equals(actionInputData.getActionType()))
                    .findAny()
                    .orElse(null);
            if (fatalAction == null){
                jsonObject.put("message", "error -> " + actionInputData.getTitle()
                + "had already been rated");
            }
        }
    }

    private void rateSerial(final JSONObject jsonObject) {
        String gradString = String.valueOf(this.actionInputData.getGrade());
        jsonObject.put("message", "success -> " + this.actionInputData.getTitle()
        + " was rated with " + gradString + " by "
        + this.actionInputData.getUsername());
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
            String showName = actionInputData.getTitle();
            if (map.containsKey(showName)) {
                if (actionInputData.getSeasonNumber() == 0) {
                    rateMovie(jsonObject);
                } else {
                    rateSerial(jsonObject);
                }
            } else {
                jsonObject.put("message", "error -> " + showName + " is not seen");
            }
        }
        this.jsonArray.add(jsonObject);
        }
}
