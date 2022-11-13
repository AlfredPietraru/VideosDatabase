package actioncontrol.Queries;

import actor.ActorsAwards;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Input;
import org.json.simple.JSONArray;
import org.json.JSONObject;

import java.util.*;


import static utils.Utils.stringToAwards;

public final class AwardsQuery extends  QueryType{
    public AwardsQuery(Input input, JSONArray jsonArray, ActionInputData actionInputData) {
        super(input, jsonArray, actionInputData);
    }


    private boolean  containsAllAwards(ActorInputData actorInputData,
                                       List<String> searchedAwards) {
        Map<ActorsAwards, Integer> awardsMap = actorInputData.getAwards();
        List<String> awardsFound = searchedAwards.stream()
                .filter(elem -> awardsMap.containsKey(stringToAwards(elem)))
                .toList();
        return awardsFound.size() == searchedAwards.size();
    }

    public int getAwardsNoActor(ActorInputData actorInputData,
                                 List<String> searchedAwards) {
        Map<ActorsAwards, Integer> map = actorInputData.getAwards();
        int result = 0;
        for (String searchedAward : searchedAwards) {
            result = result + map.get(stringToAwards(searchedAward));
        }
        return result;
    }

    @Override
    public void execute() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.actionInputData.getActionId());
        List<String> searchedAwards = this.actionInputData.getFilters().get(3);
        List<ActorInputData> actorsWithAllAwards = this.input.getActors().stream()
                .filter(elem -> containsAllAwards(elem, searchedAwards)).toList();
        Map<String, Integer> mapOfActors = new HashMap<>();
        actorsWithAllAwards.forEach(elem ->
                mapOfActors.put(elem.getName(), getAwardsNoActor(elem, searchedAwards)));
        List<Map.Entry<String, Integer>> finalList = new LinkedList<>(mapOfActors.entrySet());
        finalList.sort((o1, o2) -> (o1.getValue().compareTo(o2.getValue())));
        jsonObject.put("message", "Query result: " + Arrays.toString(finalList.stream().
                limit(actionInputData.getNumber()).toList().stream().
                limit(this.actionInputData.getNumber()).toArray()));
        jsonArray.add(jsonObject);
    }
}
