package actioncontrol.Queries;

import fileio.*;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.*;
import java.util.stream.Collectors;

public class LongestQuery extends   QueryType {
    public LongestQuery(Input input, JSONArray jsonArray, ActionInputData actionInputData) {
        super(input, jsonArray, actionInputData);
    }

    private List<MovieInputData> filterMovies() {
        return this.input.getMovies().stream()
                .filter(movie -> movie.checkShowType(this.actionInputData))
                .collect(Collectors.toList());
    }

    @Override
    public void execute() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.actionInputData.getActionId());
        List<MovieInputData> filteredMovies = new ArrayList<>(filterMovies());
        if (this.actionInputData.getSortType().equals("asc")) {
            Collections.sort(filteredMovies, (o1, o2) -> o1.getDuration() - o2.getDuration());
        } else {
            Collections.sort(filteredMovies, (o1, o2) -> o2.getDuration() - o1.getDuration());
        }

        List<String> finalStringList = new ArrayList<>();
        filteredMovies.forEach(movie -> finalStringList.add(movie.getTitle()));
        jsonObject.put("message", "Query result: " + Arrays.toString(finalStringList.stream().
                limit(actionInputData.getNumber()).toList().stream().
                limit(this.actionInputData.getNumber()).toArray()));
        jsonArray.add(jsonObject);
    }
}
