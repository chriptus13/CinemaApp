package pt.isel.ls.model;

import org.json.JSONObject;
import pt.isel.ls.model.entities.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

public class MovieAPI {
    private static final String MOVIE_DB_HOST = "https://api.themoviedb.org/3/";
    private static final String MOVIE_DB_MOVIE = "movie/{0,number,#}?api_key={1}";
    // Get a token @ https://www.themoviedb.org/documentation/api
    private static final String MOVIE_DB_APIKEY = "<token>";

    public static Movie getMovieFromMovieDB(int id) {
        try {
            URL url = new URL(MessageFormat.format(MOVIE_DB_HOST + MOVIE_DB_MOVIE, id, MOVIE_DB_APIKEY));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            if(con.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) return null;

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder output = new StringBuilder();
            br.lines().forEach(output::append);

            JSONObject json = new JSONObject(output.toString());

            return new Movie(id, json.getString("title"),
                    Integer.parseInt(json.getString("release_date").split("-")[0]),
                    json.getInt("runtime"));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
