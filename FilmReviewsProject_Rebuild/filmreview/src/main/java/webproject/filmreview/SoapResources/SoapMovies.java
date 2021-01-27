package webproject.filmreview.SoapResources;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import webproject.filmreview.Models.Genres;
import webproject.filmreview.Models.Movie;
import webproject.filmreview.Models.MoviePojo;
import webproject.filmreview.Models.MoviesListPojo;
import webproject.filmreview.Utilities.MovieService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public class SoapMovies
{
    private MovieService movieService = new MovieService();

    @WebMethod(action = "get_all_movies_soap", operationName = "GetAllMovies")
    @WebResult(partName = "Resulting movies list")
    public MoviesListPojo doGetAllMoviesSOAP()
    {
        List<Movie> moviesList = movieService.getAllMovies(); 
        MoviesListPojo pojo = new MoviesListPojo();
        if (moviesList.size() == 0)
        {
            List<Movie> emptyList = new ArrayList<>();
            pojo.setMoviesList(emptyList);
            return pojo;
        }
        pojo.setMoviesList(moviesList);
        return pojo;
    }

    @WebMethod(action = "get_movie_byId_soap", operationName = "GetMovieById")
    @WebResult(partName = "Resulting movie")
    public MoviePojo getMovieByIdSoap(@WebParam(partName = "movieId") String movieId) throws InvalidInputException
    {
        try
        {
            long id = Long.valueOf(movieId);
            Movie mov = movieService.getMovieById(id);
            if(mov == null)
            {
                throw new InvalidInputException("Not found", "The provided Id does not map to any movie.");
            }
            else
            {
                Date releaseDate = mov.getReleaseDate();
                String toSet = formatDate(releaseDate);
                List<Genres> gnrs = mov.getGenres();
                List<String> gnrsToset = new ArrayList<>();
                for(Genres g : gnrs)
                {
                    gnrsToset.add(g.name());
                }
                MoviePojo movie = new MoviePojo(id, mov.getName(), toSet, mov.getRating(),
                mov.getDescription(), mov.getMainActors(), gnrsToset);
                return movie;
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Invalid input", "The provided input does not represent a valid Id format.");
        }
    }

    @WebMethod(action = "get_movie_byName_soap", operationName = "GetMovieByName")
    @WebResult(partName = "Resulting movie")
    public MoviePojo getMovieByNameSoap(@WebParam(partName = "movieName") String movieName) throws InvalidInputException
    {
       if(movieName == "")
       {
           throw new InvalidInputException("Invalid input", "Please provide the movie's name.");
       }
       else
       {
           Movie mov = movieService.getMovieByName(movieName);
           if(mov == null)
           {
               throw new InvalidInputException("Not found", "The movie with the Name=" + movieName + " does not exist in the database.");
           }
           else
           {
                Date releaseDate = mov.getReleaseDate();
                String toSet = formatDate(releaseDate);
                List<Genres> gnrs = mov.getGenres();
                List<String> gnrsToset = new ArrayList<>();
                for(Genres g : gnrs)
                {
                    gnrsToset.add(g.name());
                }
                MoviePojo movie = new MoviePojo(mov.getId(), movieName, toSet, mov.getRating(),
                mov.getDescription(), mov.getMainActors(), gnrsToset);
                return movie;
           }
       }
    }

    @WebMethod(action = "add_movie_soap", operationName = "AddMovie")
    @WebResult(partName = "Result")
    public String postMovieSoap(@WebParam(partName = "MovieInput") MoviePojo movie) throws InvalidInputException
    {
        if((movie.getDescription() == "") || (movie.getName() == "") || (movie.getMainActors() == "")
        || (movie.getRating() == 0.0f) || (movie.getGenres().size() == 0))
        {
            throw new InvalidInputException("Input error", "All detail fields for the movie must be filled.");
        }
        List<String> genres = movie.getGenres();
        List<Genres> listToSet = new ArrayList<>();
        for(String g : genres)
        {
            g = g.toUpperCase();
            if((g.equals("COMEDY")) || (g.equals("ACTION")) || (g.equals("BIOGRAPHY")) || (g.equals("THRILLER")) || (g.equals("ROMANCE")) 
            || (g.equals("HORROR")) || (g.equals("DOCUMENTARY")) || (g.equals("DRAMA")))
            {
                Genres toadd = Genres.valueOf(g);
                listToSet.add(toadd);
            }
            else
            {
                throw new InvalidInputException("Genres input error", "The genres that you can set can only be either one value of: "
                + "ACTION, COMEDY, HORROR, BIOGRAPHY, THRILLER, ROMANCE, DRAMA, DOCUMENTARY.");
            }
        }
        String releaseDate = movie.getReleaseDate();
        boolean flag = checkDateFormat(releaseDate);
        if(flag)
        {
            Date toComp = null;
            try 
            {
                toComp = new SimpleDateFormat("dd/MM/yyyy").parse(releaseDate);
                Date today = new Date();
                if(toComp.after(today))
                {
                    throw new InvalidInputException("Input error", "The release date is after the current date.");
                }
                Movie toAdd = new Movie(0, movie.getName(), toComp, movie.getRating(),
                movie.getDescription(), movie.getMainActors(), listToSet);
                Movie value = movieService.addMovie(toAdd);
                if(value == null)
                {
                    throw new InvalidInputException("Movie error", "This movie already exists in the database.");
                }
                else
                {
                    return "Movie with Id=" + value.getId() + " has been added to the database.";
                }
            } 
            catch (ParseException e) 
            {
                throw new InvalidInputException("Date error", "The date you have entered cannot be converted to an appropriate date.");
            }
        }
        else
        {
            throw new InvalidInputException("Date input error", "The date must be in a dd/MM/yyyy format.");
        }
    }

    @WebMethod(action = "update_movie_soap", operationName = "UpdateMovie")
    @WebResult(partName = "Result")
    public String updateMovieSoap(@WebParam(partName = "MovieInput") MoviePojo movie) throws InvalidInputException
    {
        if((movie.getDescription() == "") || (movie.getName() == "") || (movie.getMainActors() == "")
        || (movie.getRating() == 0.0f) || (movie.getGenres().size() == 0))
        {
            throw new InvalidInputException("Input error", "All detail fields for the movie must be filled.");
        }
        List<String> genres = movie.getGenres();
        List<Genres> listToSet = new ArrayList<>();
        for(String g : genres)
        {
            g = g.toUpperCase();
            if((g.equals("COMEDY")) || (g.equals("ACTION")) || (g.equals("BIOGRAPHY")) || (g.equals("THRILLER")) || (g.equals("ROMANCE")) 
            || (g.equals("HORROR")) || (g.equals("DOCUMENTARY")) || (g.equals("DRAMA")))
            {
                Genres toadd = Genres.valueOf(g);
                listToSet.add(toadd);
            }
            else
            {
                throw new InvalidInputException("Genres input error", "The genres that you can set can only be either one value of: "
                + "ACTION, COMEDY, HORROR, BIOGRAPHY, THRILLER, ROMANCE, DRAMA, DOCUMENTARY.");
            }
        }
        String releaseDate = movie.getReleaseDate();
        boolean flag = checkDateFormat(releaseDate);
        if(flag)
        {
            Date toComp = null;
            try 
            {
                toComp = new SimpleDateFormat("dd/MM/yyyy").parse(releaseDate);
                Date today = new Date();
                if(toComp.after(today))
                {
                    throw new InvalidInputException("Input error", "The release date is after the current date.");
                }
                Movie updatedMovie = new Movie(movie.getId(), movie.getName(), toComp, movie.getRating(),
                movie.getDescription(), movie.getMainActors(), listToSet);
                Movie value = movieService.updateMovie(updatedMovie);
                if(value == null)
                {
                    throw new InvalidInputException("Movie error", "The movie with Id=" + movie.getId() + " does not exist in the database.");
                }
                else
                {
                    return "Movie with Id=" + value.getId() + " has been updated.";
                }
            } 
            catch (ParseException e) 
            {
                throw new InvalidInputException("Date error", "The date you have entered cannot be converted to an appropriate date.");
            }
        }
        else
        {
            throw new InvalidInputException("Date input error", "The date must be in a dd/MM/yyyy format.");
        }
    }

    @WebMethod(action = "delete_movie_soap", operationName = "DeleteMovie")
    @WebResult(partName = "Result")
    public String deleteMovieSoap(@WebParam(partName = "movieId") String movieId) throws InvalidInputException
    {
        try
        {
            long id = Long.valueOf(movieId);
            boolean result = movieService.deleteMovie(id);
            if(result)
            {
                return "Movie with Id=" + id + " has been deleted.";
            }
            else
            {
                throw new InvalidInputException("Error with the Id input", "The movie with Id=" + id + " does not exist in the database.");
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Error with input", "The provided input does not represent a valid Id format.");
        }
    }

    private String formatDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println(month);
        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);
        String str = "";
        if(day <=9 )
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

    private boolean checkDateFormat(String date)
    {
        String[] comps = date.split("/");
        if(comps.length != 3)
        {
            return false;
        }
        if((comps[0].isEmpty()) || (comps[1].isEmpty()) || (comps[2].isEmpty()))
        {
            return false;
        }
        if((comps[0].length() != 2) || (comps[1].length() != 2) || (comps[2].length() != 4))
        {
            return false;
        }
        String day = comps[0];
        char firstOfDay = day.charAt(0);
        if(firstOfDay == '0')
        {
            char secondOfDay = day.charAt(1);
            int secondDay = Character.getNumericValue(secondOfDay);
            if(secondDay<=0)
            {
                return false;
            }
            if(secondDay > 9)
            {
                return false;
            }
        }
        else
        {
            int dayInt = Integer.parseInt(day);
            if(dayInt < 10)
            {
                return false;
            }
            if(dayInt > 31)
            {
                return false;
            }
        }
        String month = comps[1];
        char firstOfMonth = month.charAt(0);
        if(firstOfMonth == '0')
        {
            char secondOfMonth = month.charAt(1);
            int secondMonth = Character.getNumericValue(secondOfMonth);
            if(secondMonth <=0)
            {
                return false;
            }
            if(secondMonth > 9)
            {
                return false;
            }
        }
        else
        {
            int monthInt = Integer.parseInt(month);
            if(monthInt < 0)
            {
                return false;
            }
            if(monthInt > 12)
            {
                return false;
            }
        }
        String year = comps[2];
        int yearInt = Integer.parseInt(year);
        if(yearInt < 1900)
        {
            return false;
        }
        if(yearInt > 2022)
        {
            return false;
        }
        return true;
    }

}
