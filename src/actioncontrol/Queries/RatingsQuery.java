package actioncontrol.Queries;

import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONArray;

public class RatingsQuery extends QueryType{
    public RatingsQuery(Input input, JSONArray jsonArray, ActionInputData actionInputData) {
        super(input, jsonArray, actionInputData);
    }

    @Override
    public void execute() {

    }
}