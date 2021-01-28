package webproject.filmreview.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webproject.filmreview.Models.AuthenticationDetails;
import webproject.filmreview.Models.Episode;
import webproject.filmreview.Models.Genres;
import webproject.filmreview.Models.Movie;
import webproject.filmreview.Models.Season;
import webproject.filmreview.Models.Series;
import webproject.filmreview.Models.User;

public class Database 
{

    private static Map<Long, User> users = new HashMap<>();
    private static Map<Long, Movie> movies = new HashMap<>();
    private static Map<Long, Series> series = new HashMap<>();
    private static Map<Long, AuthenticationDetails> sessions = new HashMap<>();

    public static Map<Long, User> getAllUsers()
    {
        return users;
    }

    public static Map<Long, Movie> getAllMovies()
    {
        if(movies.isEmpty())
        {
            seedDataWithMovies();
        }
        return movies;
    }

    public static Map<Long, Series> getAllSeries()
    {
        if(series.isEmpty())
        {
            seedDataWithSeries();
        }
        return series;
    }

    public static Map<Long, AuthenticationDetails> getSessions()
    {
        return sessions;
    }

    private static void seedDataWithMovies()
    {
        String sDate1 = "13/06/1980";
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!date1.equals(null)) {
            List<Genres> genres = new ArrayList<>();
            genres.add(Genres.DRAMA);
            genres.add(Genres.HORROR);
            Movie mov = new Movie(Long.valueOf(1), "The Shining", date1, (float) 8.4,
                    "A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future.",
                    "Jack Nicholson, Shelley Duvall, Danny Lloyd", genres);
            movies.put(Long.valueOf(1), mov);
        }
        String sDate2 = "20/07/1988";
        Date date2 = null;
        try
        {
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        if(!date2.equals(null))
        {
            List<Genres> genres2 = new ArrayList<>();
            genres2.add(Genres.ACTION);
            genres2.add(Genres.THRILLER);
            Movie mov2 = new Movie(Long.valueOf(2), "Die Hard", date2, (float)8.2, 
            "An NYPD officer tries to save his wife and several others taken hostage by German terrorists during a Christmas party at the Nakatomi Plaza in Los Angeles.", 
            "Bruce Willis, Bonnie Bedelia, Reginald VelJohnson", genres2);
            movies.put(Long.valueOf(2), mov2);
        }
    }

    private static void seedDataWithSeries()
    {
        List<Season> seasonsList = new ArrayList<>();
        List<Episode> episodesList = new ArrayList<>();
        List<Genres> genres = new ArrayList<>();
        genres.add(Genres.DRAMA);
        genres.add(Genres.THRILLER);
        genres.add(Genres.DOCUMENTARY);
        Series s = new Series(Long.valueOf(1), "Chernobyl", (float)9.4, 
        "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.", 
        seasonsList, genres);
        Season ss = new Season(Long.valueOf(1), 1, s.getId(), (float)9.62, "The first season of Chernobyl", episodesList, 
        "Jessie Buckley, Jared Harris, Stellan Skarsgård");
        String sDate1 = "06/05/2019";
        Date date1 = null;
        try
        {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        Episode episode1 = new Episode(Long.valueOf(1), ss.getId(), (float)9.5, 
        "Plant workers and firefighters put their lives on the line to control a catastrophic April 1986 explosion at a Soviet nuclear power plant.", 
        date1, 1);
        String sDate2 = "13/05/2019";
        Date date2 = null;
        try
        {
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        Episode episode2 = new Episode(Long.valueOf(2), ss.getId(), (float)9.6, 
        "With untold millions at risk, Ulana makes a desperate attempt to reach Valery and warn him about the threat of a second explosion.", 
        date2, 2);
        String sDate3 = "20/05/2019";
        Date date3 = null;
        try
        {
            date3 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate3);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        Episode episode3 = new Episode(Long.valueOf(3), ss.getId(), (float)9.6, 
        "Valery creates a detailed plan to decontaminate Chernobyl; Lyudmilla ignores warnings about her firefighter husband's contamination.", 
        date3, 3);
        String sDate4 = "27/05/2019";
        Date date4 = null;
        try
        {
            date4 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate4);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        Episode episode4 = new Episode(Long.valueOf(4), ss.getId(), (float)9.5, 
        "Valery and Boris attempt to find solutions to removing the radioactive debris; Ulana attempts to find out the cause of the explosion.", 
        date4, 4);
        String sDate5 = "03/06/2019";
        Date date5 = null;
        try
        {
            date5 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate5);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        Episode episode5 = new Episode(Long.valueOf(5), ss.getId(), (float)9.9, 
        "Valery, Boris and Ulana risk their lives and reputations to expose the truth about Chernobyl.", 
        date5, 5);
        episodesList.add(episode1);
        episodesList.add(episode2);
        episodesList.add(episode3);
        episodesList.add(episode4);
        episodesList.add(episode5);
        ss.setEpisodes(episodesList);
        seasonsList.add(ss);
        s.setSeasons(seasonsList);
        series.put(s.getId(), s);
    }

}