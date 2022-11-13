package actioncontrol;
import actioncontrol.Commands.CommandInvoker;
import actioncontrol.Queries.QueryInvoker;
import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONArray;

public class Invoker {
    private  final Input input;
    private final JSONArray jsonArray;
    private final CommandInvoker commandInvoker;
    private final QueryInvoker queryInvoker;
    private final RecommendationInvoker recommendationInvoker;

    public Invoker(final Input input, final JSONArray jsonArray) {
        this.input = input;
        this.jsonArray = jsonArray;
        this.commandInvoker = new CommandInvoker(input, jsonArray);
        this.queryInvoker = new QueryInvoker(input, jsonArray);
        this.recommendationInvoker = new RecommendationInvoker(input, jsonArray);
    }

    public void execute(ActionInputData actionInputData) {

            if (actionInputData.getActionType().equals("command")) {
                this.commandInvoker.setActionInputData(actionInputData);
                this.commandInvoker.execute();
            }
            if (actionInputData.getActionType().equals("query")) {
                this.queryInvoker.setActionInputData(actionInputData);
                this.queryInvoker.execute();
            }
            if (actionInputData.getActionType().equals("recommendation")) {
                System.out.println("recommend");
            }
    }

}