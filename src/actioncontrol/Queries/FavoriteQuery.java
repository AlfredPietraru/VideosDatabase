package actioncontrol.Queries;

import fileio.*;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.*;
import java.util.stream.Collectors;

public class FavoriteQuery extends QueryType {
    public FavoriteQuery(Input input, JSONArray jsonArray, ActionInputData actionInputData) {
        super(input, jsonArray, actionInputData);
    }

    private boolean checkIfFavoriteToUser(ShowInput movie, UserInputData user) {
        List<String> userFavoriteList = user.getFavoriteMovies();
        String findTheMovieInFavorites = userFavoriteList.stream().
                filter(string -> movie.getTitle().equals(string))
                .findAny()
                .orElse(null);
        return findTheMovieInFavorites != null;
    }

    private int checkShowForAllUsers(ShowInput movie) {
        List<UserInputData> eachOccurenceOfFavorite = this.input.getUsers().stream()
                .filter(user -> checkIfFavoriteToUser(movie, user))
                .toList();
        return eachOccurenceOfFavorite.size();
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
        jsonObject.put("id", actionInputData.getActionId());
        List<ShowInput> certainMovieTypes;
        if(this.actionInputData.getObjectType().equals("movies")) {
         certainMovieTypes = new ArrayList<>(filterMovies());
        } else {
            certainMovieTypes = new ArrayList<>(filterShows());
        }
        List<SpecialListNode<ShowInput, Integer>> finalList = new ArrayList<>();
        certainMovieTypes.forEach(movie ->
                finalList.add(new SpecialListNode<>(movie, checkShowForAllUsers(movie))));
        if (this.actionInputData.getSortType().equals("asc")) {
            finalList.sort((o1, o2) -> o1.getValueToIt() - o2.getValueToIt());
        } else {
            finalList.sort((o1, o2) -> o2.getValueToIt() - o1.getValueToIt());
        }
        List<String> outputList = new ArrayList<>();
        finalList.forEach(listNode -> outputList.add(listNode.getNode().getTitle()));
        jsonObject.put("message", "Query result: " + Arrays.toString(outputList.stream().
                limit(this.actionInputData.getNumber()).toList().stream().
                limit(this.actionInputData.getNumber()).toArray()));
        jsonArray.add(jsonObject);
    }
}
