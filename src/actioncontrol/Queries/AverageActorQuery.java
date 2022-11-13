package actioncontrol.Queries;

import fileio.ActionInputData;
import fileio.Input;
import org.json.JSONObject;
import org.json.simple.JSONArray;

public class AverageActorQuery extends  QueryType{

    public AverageActorQuery(Input input, JSONArray jsonArray, ActionInputData actionInputData) {
        super(input, jsonArray, actionInputData);
    }

    @Override
    public void execute() {
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("id", this.actionInputData.getActionId());
    }
}
