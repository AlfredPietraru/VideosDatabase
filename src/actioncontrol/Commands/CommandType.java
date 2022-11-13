package actioncontrol.Commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import org.json.simple.JSONArray;

public abstract class CommandType {

    final Input input;
    final JSONArray jsonArray;
    public ActionInputData actionInputData;

    public CommandType(final Input input, final JSONArray jsonArray,
                       ActionInputData actionInputData) {
        this.input = input;
        this.jsonArray = jsonArray;
        this.actionInputData = actionInputData;
    }

    public UserInputData checkUsernameExistence() {
        return input.getUsers().stream()
                .filter(a -> a.getUsername().equals(this.actionInputData.getUsername()))
                .findAny()
                .orElse(null);
    }

    public String checkMovieNameInFavorites(final UserInputData userInputData) {
        return userInputData.getFavoriteMovies().stream().
                filter(a -> a.equals(actionInputData.getTitle()))
                .findAny()
                .orElse(null);
    }

    public abstract void execute();
    {
        System.out.println("it should do something");
    }


}
