package actioncontrol.Queries;

import fileio.*;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.*;
import java.util.stream.Collectors;

public class MostViewedQuery extends QueryType{
    public MostViewedQuery(Input input, JSONArray jsonArray, ActionInputData actionInputData) {
        super(input, jsonArray, actionInputData);
    }

    private Integer showView(ShowInput show) {
        Integer getViews = 0;
        List<UserInputData> usersSeenTheShow = this.input.getUsers().stream()
                .filter(user -> user.getHistory().containsKey(show.getTitle()))
                .toList();
        for (UserInputData userInputData : usersSeenTheShow) {
            getViews = getViews + userInputData.getHistory().get(show.getTitle());
        }
        return getViews;
    }

    private List<MovieInputData> filterMovies() {
        return this.input.getMovies().stream()
                .filter(movie -> movie.checkShowType(this.actionInputData))
                .collect(Collectors.toList());
    }

    private List<SerialInputData> filterShows(){
        return this.input.getSerials().stream()
                .filter(serial -> serial.checkShowType(this.actionInputData))
                .collect(Collectors.toList());
    }

    @Override
    public void execute() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.actionInputData.getActionId());
        List<ShowInput> certainMovieTypes;
        if (this.actionInputData.getObjectType().equals("movies")) {
            certainMovieTypes = new ArrayList<>(filterMovies());
        } else {
            certainMovieTypes = new ArrayList<>(filterShows());
        }
        List<SpecialListNode<ShowInput, Integer>> listAndViews = new ArrayList<>();
        certainMovieTypes.stream()
                .filter(show -> showView(show) != 0)
                .forEach(show -> listAndViews.add(new SpecialListNode<>(show, showView(show))));


        if (this.actionInputData.getSortType().equals("asc")) {
            listAndViews.sort(((o1, o2) -> o1.getValueToIt() - o2.getValueToIt()));
        } else {
            listAndViews.sort(((o1, o2) -> o2.getValueToIt() - o1.getValueToIt()));
        }
        List<String> outputList = new ArrayList<>();
        listAndViews.forEach(listNode -> outputList.add(listNode.getNode().getTitle()));
        jsonObject.put("message", "Query result: " + Arrays.toString(outputList.stream().
                limit(this.actionInputData.getNumber()).toList().stream().
                limit(this.actionInputData.getNumber()).toArray()));
        jsonArray.add(jsonObject);
    }
}
