package webproject.filmreview.Utilities;

import java.util.HashMap;
import java.util.Map;
import webproject.filmreview.Models.Movie;
import webproject.filmreview.Models.Series;
import webproject.filmreview.Models.User;

public class Database 
{

    private static Map<Long, User> users = new HashMap<>();
    private static Map<Long, Movie> movies = new HashMap<>();
    private static Map<Long, Series> series = new HashMap<>();

    public static Map<Long, User> getAllUsers()
    {
        return users;
    }

    public static Map<Long, Movie> getAllMovies()
    {
        return movies;
    }

    public static Map<Long, Series> getAllSeries()
    {
        return series;
    }

}