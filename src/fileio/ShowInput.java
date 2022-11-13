package fileio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * General information about show (video), retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public abstract class ShowInput {
    /**
     * Show's title
     */
    private final String title;
    /**
     * The year the show was released
     */
    private final int year;
    /**
     * Show casting
     */
    private final ArrayList<String> cast;
    /**
     * Show genres
     */
    private final ArrayList<String> genres;

    public boolean checkShowGenre(ActionInputData actionInputData) {

        if (actionInputData.getFilters().get(1) == null ||
                actionInputData.getFilters().get(1).get(0) == null) {
            return true;
        }
        if (this.getGenres() == null) {
            return false;
        }

        for (int i = 0; i < actionInputData.getFilters().get(1).size(); i++){
            int finalI = i;
            String foundYou = this.getGenres().stream()
                    .filter(str -> actionInputData.getFilters().get(1).get(finalI).equals(str))
                    .findAny()
                    .orElse(null);
            if( foundYou == null) {
                return false;
            }
       }
        return true;
    }

    public boolean checkYear(ActionInputData actionInputData) {
        String yearString = String.valueOf(this.getYear());
        if (actionInputData.getFilters().get(0) == null) {
            return true;
        }
        if (actionInputData.getFilters().get(0).get(0) == null) {
            return true;
        }
        return actionInputData.getFilters().get(0).get(0).equals(yearString);
    }

    public boolean checkShowType(ActionInputData actionInputData) {
        return this.checkShowGenre(actionInputData) && this.checkYear(actionInputData);
    }

    public ShowInput(final String title, final int year,
                     final ArrayList<String> cast, final ArrayList<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
    }

    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }
    public final ArrayList<String> getCast() {
        return cast;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }
}
