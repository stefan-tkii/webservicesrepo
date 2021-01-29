package filmsproject.soapwsdl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import webproject.filmreview.soapresources.Genres;
import webproject.filmreview.soapresources.InvalidInputException_Exception;
import webproject.filmreview.soapresources.Movie;
import webproject.filmreview.soapresources.MovieID;
import webproject.filmreview.soapresources.MoviePojo;
import webproject.filmreview.soapresources.MoviesListPojo;
import webproject.filmreview.soapresources.NotFoundException_Exception;
import webproject.filmreview.soapresources.SoapFilms;
import webproject.filmreview.soapresources.SoapFilmsService;

public class SoapMoviesClientGetter
{

    private SoapFilmsService service;
    private SoapFilms port;

    public SoapMoviesClientGetter()
    {
        this.service = new SoapFilmsService();
        this.port = this.service.getSoapFilmsPort();
    }

    public static void main(String[] args)
    {
        SoapMoviesClientGetter client = new SoapMoviesClientGetter();
        client.getAllMovies();
        client.getMovieById("1");
    }

    public void getAllMovies()
    {
        MoviesListPojo result = port.getAllMovies();
        MoviesListPojo.Movies movies = result.getMovies();
        List<Movie> list = movies.getMovie();
        System.out.println("Resulting movies list: ");
        for(Movie m : list)
        {
            System.out.println("Id: " + m.getId());
            System.out.println("Name: " + m.getName());
            System.out.println("Main actors: " + m.getMainActors());
            System.out.println("Rating: " + m.getRating());
            System.out.println("Description: " + m.getDescription());
            System.out.println("Release date: " + m.getReleaseDate());
            String genres = "Genres: ";
            List<Genres> gnrs = m.getGenres();
            for(Genres g : gnrs)
            {
                genres = genres + g.name() + ", ";
            }
            System.out.println(genres);
            System.out.println("\n");
        }
    }

    public void getMovieById(String movieId)
    {
        MovieID movieID = new MovieID();
        movieID.setId(movieId);
        try
        {
            MoviePojo m = port.getMovieById(movieID);
            System.out.println("Result");
            System.out.println("Id: " + m.getId());
            System.out.println("Name: " + m.getName());
            System.out.println("Main actors: " + m.getMainActors());
            System.out.println("Rating: " + m.getRating());
            System.out.println("Description: " + m.getDescription());
            System.out.println("Release date: " + m.getReleaseDate());
            String genres = "Genres: ";
            MoviePojo.Genres gens = m.getGenres();
            List<String> gnrs = gens.getGenre();
            for(String g : gnrs)
            {
                genres = genres + g + ", ";
            }
            System.out.println(genres);
            System.out.println("\n");
        }
        catch(InvalidInputException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public String formatDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        String str = "";
        if(day <= 9 )
        {
            str = str + "0" + day + "/";
        }
        else
        {
            str = str + day + "/";
        }
        if(month <= 9)
        {
            str = str + "0" + month + "/";
        }
        else
        {
            str = str + month + "/";
        }
        str = str + year;
        return str;
    }

}
