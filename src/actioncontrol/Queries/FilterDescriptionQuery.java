package actioncontrol.Queries;

import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Input;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterDescriptionQuery extends  QueryType {
    public FilterDescriptionQuery(Input input, JSONArray jsonArray, ActionInputData actionInputData) {
        super(input, jsonArray, actionInputData);
    }

    private boolean checkOneActor(ActorInputData actor, List<String> wordsToFilter) {
        List<String> wordsFound = wordsToFilter.stream()
                .filter(s -> actor.getCareerDescription().contains(s)).toList();
        return wordsFound.size() == wordsToFilter.size();
    }
    @Override
    public void execute() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", actionInputData.getActionId());
        List<ActorInputData> actorList = input.getActors();
        List<String> wordsToFilter = actionInputData.getFilters().get(2);
        List<ActorInputData> filteredActorsList = actorList.stream()
                .filter(actor -> checkOneActor(actor, wordsToFilter))
                .toList();
        if (filteredActorsList.isEmpty()) {
            jsonObject.put("message", "Query result: []");
        } else {
            List<ActorInputData> toPrintList = new ArrayList<>(filteredActorsList);
            if (actionInputData.getSortType().equals("asc")) {
                toPrintList.sort(((o1, o2) -> (o1.getName().compareTo(o2.getName()))));
            }
            if (actionInputData.getSortType().equals("desc")) {
                toPrintList.sort(((o1, o2) -> (-o1.getName().compareTo(o2.getName()))));
            }
            List<String> outputList = new ArrayList<>();
            toPrintList.forEach(listNode -> outputList.add(listNode.getName()));
            jsonObject.put("message", "Query result: " + Arrays.toString(outputList.stream().
                    limit(this.actionInputData.getNumber()).toList().stream().
                    limit(this.actionInputData.getNumber()).toArray()));
            jsonArray.add(jsonObject);
        }
        jsonArray.add(jsonObject);
    }
}
