package actioncontrol.Queries;

import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONArray;

public class QueryFactory {
    private final Input input;
    private  final JSONArray jsonArray;
    private ActionInputData actionInputData;

    public QueryFactory(Input input, JSONArray jsonArray, ActionInputData actionInputData) {
        this.input = input;
        this.jsonArray = jsonArray;
        this.actionInputData = actionInputData;
    }

    public void setActionInputData(ActionInputData actionInputData) {
        this.actionInputData = actionInputData;
    }

    public QueryType create(){
        if (actionInputData.getCriteria().equals("average"))
        {
            return new AverageActorQuery(input, jsonArray, actionInputData);
        }
        if (actionInputData.getCriteria().equals("awards")) {
            return new AwardsQuery(input, jsonArray, actionInputData);
        }
        if (actionInputData.getCriteria().equals("filter_description")) {
            return new FilterDescriptionQuery(input, jsonArray, actionInputData);
        }
        if (actionInputData.getCriteria().equals("favorite")) {
            return new FavoriteQuery(input, jsonArray, actionInputData);
        }
        if(actionInputData.getCriteria().equals("ratings")) {
            return new RatingsQuery(input, jsonArray, actionInputData);
        }
        if(actionInputData.getCriteria().equals("longest")) {
            return new LongestQuery(input, jsonArray, actionInputData);
        }
        if(actionInputData.getCriteria().equals("most_viewed")) {
            return new MostViewedQuery(input, jsonArray, actionInputData);
        }

        return null;
    }

}
