package filmsproject.soapwsdl;

import java.util.ArrayList;
import java.util.List;

import webproject.filmreview.soapresources.InvalidInputException_Exception;
import webproject.filmreview.soapresources.MovieID;
import webproject.filmreview.soapresources.MoviePojo;
import webproject.filmreview.soapresources.NotAuthorizedException_Exception;
import webproject.filmreview.soapresources.NotFoundException_Exception;
import webproject.filmreview.soapresources.SoapFilms;
import webproject.filmreview.soapresources.SoapFilmsService;
import webproject.filmreview.soapresources.SoapResponse;
import webproject.filmreview.soapresources.TokenPojo;
import webproject.filmreview.soapresources.MoviePojo.Genres;

public class SoapMoviesClientPoster 
{

    private SoapFilmsService service;
    private SoapFilms port;
    private static final String adminToken = "5js2PstOpYJ7uZebUWnjYv0se44+ep0ttjAHA9gKG5M=";

    public SoapMoviesClientPoster()
    {
        this.service = new SoapFilmsService();
        this.port = this.service.getSoapFilmsPort();
    }

    public static void main(String[] args) 
    {
        SoapMoviesClientPoster client = new SoapMoviesClientPoster();
        //client.addMovie(); 
        //client.updateMovie("3");   
        client.deleteMovie("3");
    }
    
    public void addMovie()
    {
        MoviePojo movieToAdd = new MoviePojo();
        movieToAdd.setId(0);
        movieToAdd.setDescription("New movie description.");
        movieToAdd.setMainActors("Leonardo Di Caprio, Jack Nicholson");
        movieToAdd.setName("Newest movie");
        movieToAdd.setReleaseDate("09/10/2011");
        movieToAdd.setRating((float)8.9);
        List<String> genres = new ArrayList<>();
        genres.add("comedy");
        genres.add("action");
        Genres value = new Genres();
        value.setGenre(genres);
        movieToAdd.setGenres(value);
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        try 
        {
            SoapResponse addResponse = port.addMovie(movieToAdd, token);
            System.out.println(addResponse.getDetails());
        } 
        catch (InvalidInputException_Exception | NotAuthorizedException_Exception e) 
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void updateMovie(String movieId)
    {
        MoviePojo update = new MoviePojo();
        update.setId(Long.valueOf(movieId));
        update.setDescription("New updated movie description.");
        update.setMainActors("Leonardo Di Caprio, Jack Nicholson");
        update.setName("Newest movie with update");
        update.setReleaseDate("09/10/2015");
        update.setRating((float)8.9);
        List<String> genres = new ArrayList<>();
        genres.add("comedy");
        genres.add("horror");
        Genres value = new Genres();
        value.setGenre(genres);
        update.setGenres(value);
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        try 
        {
            SoapResponse updateResponse = port.updateMovie(update, token);
            System.out.println(updateResponse.getDetails());
        } 
        catch (InvalidInputException_Exception | NotAuthorizedException_Exception | NotFoundException_Exception e) 
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void deleteMovie(String movieId)
    {
        MovieID movieID = new MovieID();
        movieID.setId(movieId);
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        try
        {
            SoapResponse deleteResponse = port.deleteMovie(movieID, token);
            System.out.println(deleteResponse.getDetails());
        }
        catch(InvalidInputException_Exception | NotAuthorizedException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

}