package actioncontrol.Queries;

import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONArray;

public abstract class QueryType {

    public final Input input;
    public   final JSONArray jsonArray;
    public ActionInputData actionInputData;

    public QueryType(Input input, JSONArray jsonArray, ActionInputData actionInputData) {
        this.input = input;
        this.jsonArray = jsonArray;
        this.actionInputData = actionInputData;
    }

    public abstract void execute();

}
