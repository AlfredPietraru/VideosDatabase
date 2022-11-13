package actioncontrol.Queries;

import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONArray;

public final class QueryInvoker {
    private final Input input;
    private ActionInputData actionInputData = null;

    private QueryFactory queryFactory = null;
    private final JSONArray jsonArray;
    public QueryInvoker(final Input input, final JSONArray jsonArray) {
        this.input = input;
        this.jsonArray = jsonArray;
    }

    public void setActionInputData(ActionInputData actionInputData) {

        this.actionInputData = actionInputData;
    }

    public void execute() {
        if (queryFactory == null) {
            queryFactory = new QueryFactory(this.input, this.jsonArray, this.actionInputData);
        } else {
            queryFactory.setActionInputData(this.actionInputData);
        }
        QueryType queryType = queryFactory.create();
        if (queryType != null) {
            queryType.execute();
        }
    }
}
