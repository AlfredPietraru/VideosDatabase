package actioncontrol;

import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONArray;

public class RecommendationInvoker {
    private final Input input;
    private ActionInputData actionInputData = null;
    private final JSONArray jsonArray;
    public RecommendationInvoker(final Input input, final JSONArray jsonArray) {
        this.input = input;
        this.jsonArray = jsonArray;
    }

    public void setActionInputData(ActionInputData actionInputData) {

        this.actionInputData = actionInputData;
    }
}
