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

import webproject.filmreview.Models.Episode;
import webproject.filmreview.Models.EpisodeID;
import webproject.filmreview.Models.EpisodePojo;
import webproject.filmreview.Models.FilterModel;
import webproject.filmreview.Models.Genres;
import webproject.filmreview.Models.Movie;
import webproject.filmreview.Models.MovieID;
import webproject.filmreview.Models.MoviePojo;
import webproject.filmreview.Models.MoviesListPojo;
import webproject.filmreview.Models.Season;
import webproject.filmreview.Models.SeasonID;
import webproject.filmreview.Models.SeasonPojo;
import webproject.filmreview.Models.Series;
import webproject.filmreview.Models.SeriesID;
import webproject.filmreview.Models.SeriesListPojo;
import webproject.filmreview.Models.SeriesPojo;
import webproject.filmreview.Models.SoapResponse;
import webproject.filmreview.Models.TokenPojo;
import webproject.filmreview.Utilities.MovieService;
import webproject.filmreview.Utilities.SeriesService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public class SoapFilms
{
    private MovieService movieService = new MovieService();
    private SeriesService seriesService = new SeriesService();
    private static final String adminToken = "5js2PstOpYJ7uZebUWnjYv0se44+ep0ttjAHA9gKG5M=";

    @WebMethod(action = "get_all_movies_soap", operationName = "GetAllMovies")
    @WebResult(partName = "Resulting movies list", name = "MoviesListPojo")
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

    @WebMethod(action = "get_all_movies_filtered_soap", operationName = "GetAllMoviesFiltered")
    @WebResult(partName = "Resulting movies list", name = "MoviesListPojo")
    public MoviesListPojo doGetAllMoviesFilteredSoap(@WebParam(partName = "filter", name = "FilterModel") FilterModel filter) throws InvalidInputException
    {
        MoviesListPojo pojo = new MoviesListPojo();
        List<Movie> moviesList = new ArrayList<>();
        String type = filter.getType().trim().toLowerCase();
        switch(type)
        {
            case "name":
                moviesList = movieService.getAllMoviesFiltered("Name", filter.getValue());
                break;
            case "rating":
                moviesList = movieService.getAllMoviesFiltered("Rating", filter.getValue());
                break;
            case "after date":
                moviesList = movieService.getAllMoviesFiltered("After date", filter.getValue());
                break;
            case "before date":
                moviesList = movieService.getAllMoviesFiltered("Before date", filter.getValue());
                break;
            case "genre":
                moviesList = movieService.getAllMoviesFiltered("Genre", filter.getValue());
                break;
            default:
                throw new InvalidInputException("Input error", "The filter type can only be set to one of the following values: "
                + "Name, Rating, After date, Before date, Genre");
        }
        pojo.setMoviesList(moviesList);
        return pojo;
    }

    @WebMethod(action = "get_movie_byId_soap", operationName = "GetMovieById")
    @WebResult(partName = "Resulting movie", name = "MoviePojo")
    public MoviePojo getMovieByIdSoap(@WebParam(partName = "movieId", name = "MovieID") MovieID movieId) throws InvalidInputException, NotFoundException
    {
        try
        {
            long id = Long.valueOf(movieId.getId());
            Movie mov = movieService.getMovieById(id);
            if(mov == null)
            {
                throw new NotFoundException("Not found", "The provided movie ID does not map to any movie in the database.");
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
    @WebResult(partName = "Resulting movie", name = "MoviePojo")
    public MoviePojo getMovieByNameSoap(@WebParam(partName = "movieName", name = "String") String movieName) throws InvalidInputException, NotFoundException
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
               throw new NotFoundException("Not found", "The movie with the Name=" + movieName + " does not exist in the database.");
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
                MoviePojo movie = new MoviePojo(mov.getId(), mov.getName(), toSet, mov.getRating(),
                mov.getDescription(), mov.getMainActors(), gnrsToset);
                return movie;
           }
       }
    }

    @WebMethod(action = "add_movie_soap", operationName = "AddMovie")
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse postMovieSoap(@WebParam(partName = "MovieInput", name = "MoviePojo") MoviePojo movie,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
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
                    return new SoapResponse("Movie with Id=" + value.getId() + " has been added to the database.");
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
    @WebResult(partName="Result", name = "SoapResponse")
    public SoapResponse updateMovieSoap(@WebParam(partName = "MovieInput", name = "MoviePojo") MoviePojo movie,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotFoundException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        if((movie.getDescription() == "") || (movie.getName() == "") || (movie.getMainActors() == "")
        || (movie.getRating() == 0.0f) || (movie.getGenres().size() == 0) || (movie.getId() == 0l))
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
                    throw new NotFoundException("Movie error", "The movie with Id=" + movie.getId() + " does not exist in the database.");
                }
                else
                {
                    return new SoapResponse("Movie with Id=" + value.getId() + " has been updated.");
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
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse deleteMovieSoap(@WebParam(partName = "movieId", name = "MovieID") MovieID movieId,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotFoundException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        try
        {
            long id = Long.valueOf(movieId.getId());
            boolean result = movieService.deleteMovie(id);
            if(result)
            {
                return new SoapResponse("Movie with Id=" + id + " has been deleted.");
            }
            else
            {
                throw new NotFoundException("Error with the Id input", "The movie with Id=" + id + " does not exist in the database.");
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Error with input", "The provided input does not represent a valid Id format.");
        }
    }

    @WebMethod(action = "get_all_series_soap", operationName = "GetAllSeries")
    @WebResult(partName = "Resulting series list", name = "SeriesListPojo")
    public SeriesListPojo doGetAllSeriesSOAP()
    {
        List<Series> seriesList = seriesService.getAllSeries();
        SeriesListPojo pojo = new SeriesListPojo();
        if (seriesList.size() == 0)
        {
            List<Series> emptyList = new ArrayList<>();
            pojo.setSeriesList(emptyList);
            return pojo;
        }
        pojo.setSeriesList(seriesList);
        return pojo;
    }

    @WebMethod(action = "get_all_series_filtered_soap", operationName = "GetAllSeriesFiltered")
    @WebResult(partName = "Resulting series list", name = "SeriesListPojo")
    public SeriesListPojo doGetAllSeriesFilteredSOAP(@WebParam(partName = "Filter", name = "FilterModel") FilterModel filter) throws InvalidInputException
    {
        SeriesListPojo pojo = new SeriesListPojo();
        List<Series> list = new ArrayList<>();
        String type = filter.getType().trim().toLowerCase();
        switch(type)
        {
            case "name":
                list = seriesService.getAllSeriesFiltered("Name", filter.getValue());
                break;
            case "rating":
                list = seriesService.getAllSeriesFiltered("Rating", filter.getValue());
                break;
            case "genre":
                list = seriesService.getAllSeriesFiltered("Genre", filter.getValue());
                break;
            default:
                throw new InvalidInputException("Input error", "The filter's type can only be set to one of the following values: "
                + "Name, Rating or Genre.");
        }
        pojo.setSeriesList(list);
        return pojo;
    }

    @WebMethod(action = "get_series_byId_soap", operationName = "GetSeriesById")
    @WebResult(partName = "Resulting series", name = "SeriesPojo")
    public SeriesPojo getSeriesByIdSoap(@WebParam(partName = "seriesId", name = "SeriesID") SeriesID seriesId) throws InvalidInputException, NotFoundException
    {
        try
        {
            long id = Long.valueOf(seriesId.getId());
            Series s = seriesService.getSeriesById(id);
            if(s == null)
            {
                throw new NotFoundException("Not found", "The provided Id does not map to any series.");
            }
            else
            {
                List<Genres> gnrs = s.getGenres();
                List<String> gnrsToset = new ArrayList<>();
                for(Genres g : gnrs)
                {
                    gnrsToset.add(g.name());
                }
                SeriesPojo series = new SeriesPojo(id, s.getName(), s.getRating(), s.getDescription(), 
                s.getSeasons(), gnrsToset);
                return series;
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Invalid input", "The provided input does not represent a valid Id format.");
        }
    }

    @WebMethod(action = "get_series_byName_soap", operationName = "GetSeriesByName")
    @WebResult(partName = "Resulting series", name = "SeriesPojo")
    public SeriesPojo getSeriesByNameSoap(@WebParam(partName = "seriesName", name = "String") String seriesName) throws InvalidInputException, NotFoundException
    {
       if(seriesName == "")
       {
           throw new InvalidInputException("Invalid input", "Please provide the series name.");
       }
       else
       {
           Series s = seriesService.getSeriesByName(seriesName);
           if(s == null)
           {
               throw new NotFoundException("Not found", "The series with the Name=" + seriesName + " does not exist in the database.");
           }
           else
           {
                List<Genres> gnrs = s.getGenres();
                List<String> gnrsToset = new ArrayList<>();
                for(Genres g : gnrs)
                {
                    gnrsToset.add(g.name());
                }
                SeriesPojo series = new SeriesPojo(s.getId(), s.getName(), s.getRating(), s.getDescription(), 
                s.getSeasons(), gnrsToset);
                return series;
           }
       }
    }

    @WebMethod(action = "add_series_soap", operationName = "AddSeries")
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse postSeriesSoap(@WebParam(partName = "SeriesInput", name = "SeriesPojo") SeriesPojo series,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        if((series.getDescription() == "") || (series.getName() == "")
        || (series.getRating() == 0.0f) || (series.getGenres().size() == 0))
        {
            throw new InvalidInputException("Input error", "All detail fields for the series must be filled, except for the seasons.");
        }
        List<String> genres = series.getGenres();
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
        List<Season> seasons = new ArrayList<>();
        Series toAdd = new Series(0, series.getName(), series.getRating(), 
        series.getDescription(), seasons, listToSet);
        Series value = seriesService.addSeries(toAdd);
        if(value == null)
        {
            throw new InvalidInputException("Series error", "This series already exists in the database.");
        }
        else
        {
            return new SoapResponse("Series with Id=" + value.getId() + " has been added to the database.");
        }
    }

    @WebMethod(action = "update_series_soap", operationName = "UpdateSeries")
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse updateSeriesSoap(@WebParam(partName = "SeriesInput", name = "SeriesPojo") SeriesPojo series,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotFoundException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        if((series.getDescription() == "") || (series.getName() == "")
        || (series.getRating() == 0.0f) || (series.getGenres().size() == 0) || (series.getId() == 0l))
        {
            throw new InvalidInputException("Input error", "All detail fields for the series must be filled except for the seasons.");
        }
        List<String> genres = series.getGenres();
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
        List<Season> seasons = new ArrayList<>();
        Series toUpdate = new Series(series.getId(), series.getName(), series.getRating(), 
        series.getDescription(), seasons, listToSet);
        Series value = seriesService.updateSeries(toUpdate);
        if(value == null)
        {
            throw new NotFoundException("Series error", "This series does not exist in the database.");
        }
        else
        {
            return new SoapResponse("Series with Id=" + value.getId() + " has been updated.");
        }
    }

    @WebMethod(action = "delete_series_soap", operationName = "DeleteSeries")
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse deleteSeriesSoap(@WebParam(partName = "seriesId", name = "SeriesID") SeriesID seriesId,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotFoundException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        try
        {
            long id = Long.valueOf(seriesId.getId());
            boolean result = seriesService.deleteSeries(id);
            if(result)
            {
                return new SoapResponse("Series with Id=" + id + " has been deleted.");
            }
            else
            {
                throw new NotFoundException("Error with the Id input", "The series with Id=" + id + " does not exist in the database.");
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Error with input", "The provided input does not represent a valid Id format.");
        }
    }

    @WebMethod(action = "get_season_byId_soap", operationName = "GetSeasonById")
    @WebResult(partName = "Resulting season", name = "SeasonPojo")
    public SeasonPojo getSeasonByIdSoap(@WebParam(partName = "seriesId", name = "SeriesID") SeriesID seriesId,
    @WebParam(partName = "seasonId", name = "SeasonID") SeasonID seasonId) throws InvalidInputException, NotFoundException
    {
        try
        {
            long id = Long.valueOf(seriesId.getId());
            long sesId = Long.valueOf(seasonId.getId());
            Season s = seriesService.getSeasonFromSeries(id, sesId);
            if(s == null)
            {
                throw new NotFoundException("Not found", "The provided series ID and season ID"
                + " combination does not map to any season.");
            }
            else
            {
                List<Episode> epsList = s.getEpisodes();
                SeasonPojo pojo = new SeasonPojo(s.getId(), s.getSeasonNumber(), 
                id, s.getRating(), s.getDescription(), epsList, s.getMainActors());
                return pojo;
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Invalid input", "The provided input does not represent a valid Id format.");
        }
    }

    @WebMethod(action = "post_season_to_series_soap", operationName = "PostSeasonToSeries")
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse postSeasonToSeriesSoap(@WebParam(partName = "seriesId", name = "SeriesID") SeriesID seriesId,
    @WebParam(partName = "Season", name = "SeasonPojo") SeasonPojo season,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        if((season.getDescription() == "") || (season.getSeasonNumber() == 0)
        || (season.getRating() == 0.0f) || (season.getMainActors() == ""))
        {
            throw new InvalidInputException("Input error", "All detail fields for the season must be filled, except for the episodes.");
        }
        try
        {
            long id = Long.valueOf(seriesId.getId());
            List<Episode> epsList = new ArrayList<>();
            Season toAdd = new Season(0, season.getSeasonNumber(), id, season.getRating(), 
            season.getDescription(), epsList, season.getMainActors());
            Season value = seriesService.addSeasonToSeries(id, toAdd);
            if(value == null)
            {
                throw new InvalidInputException("Input error", "Either the series with Id=" + id + " does not exist or "
               + "the provided season has already been added to this series.");
            }
            else
            {
                return new SoapResponse("Season with Id=" + value.getId() + " has been added to the series with Id=" + id + ".");
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Series Id error", "The provided Id input does not represent a valid Id format.");
        }
    }

    @WebMethod(action = "update_season_of_series_soap", operationName = "UpdateSeasonOfSeries")
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse updateSeasonOfSeriesSoap(@WebParam(partName = "seriesId", name = "SeriesID") SeriesID seriesId,
    @WebParam(partName = "Season", name = "SeasonPojo") SeasonPojo season,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotFoundException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        if((season.getDescription() == "")
        || (season.getRating() == 0.0f) || (season.getMainActors() == "") || (season.getId() == 0l))
        {
            throw new InvalidInputException("Input error", "All detail fields for the season must be filled, except for the episodes and season number.");
        }
        try
        {
            long id = Long.valueOf(seriesId.getId());
            List<Episode> epsList = new ArrayList<>();
            Season toUpdate = new Season(season.getId(), season.getSeasonNumber(), id, season.getRating(), 
            season.getDescription(), epsList, season.getMainActors());
            Season value = seriesService.updateSeasonOfSeries(id, toUpdate);
            if(value == null)
            {
                throw new NotFoundException("ID error", "Either the series with Id=" + id + " does not exist or "
                + "the provided season with Id=" + season.getId() + " is not added to the series.");
            }
            else
            {
                return new SoapResponse("Season with Id=" + value.getId() + " has been updated in the series with Id=" + id + ".");
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Series Id error", "The provided Id input does not represent a valid Id format.");
        }
    }

    @WebMethod(action = "delete_season_of_series_soap", operationName = "DeleteSeasonOfSeries")
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse deleteSeasonOfSeriesSoap(@WebParam(partName = "seriesId", name = "SeriesID") SeriesID seriesId,
    @WebParam(partName = "seasonId", name = "SeasonID") SeasonID seasonId,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotFoundException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        try
        {
            long serId = Long.valueOf(seriesId.getId());
            long sesId = Long.valueOf(seasonId.getId());
            boolean result = seriesService.deleteSeasonFromSeries(serId, sesId);
            if(result)
            {
                return new SoapResponse("Season with Id=" + sesId + " has been deleted from the series with Id=" + serId + ".");
            }
            else
            {
               throw new NotFoundException("Input error", "Either the series with Id=" + serId + " does not exist in the database"
               + " or the season with Id=" + sesId + " has not been added to this series.");
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Input error", "The provided input for seasonId and seriesId does not represent"
            + " a valid Id format.");
        }
    }

    @WebMethod(action = "get_episode_byId_soap", operationName = "GetEpisodeById")
    @WebResult(partName = "Resulting episode", name = "EpisodePojo")
    public EpisodePojo getEpisodeByIdSoap(@WebParam(partName = "seriesId", name = "SeriesID") SeriesID seriesId,
    @WebParam(partName = "seasonId", name = "SeasonID") SeasonID seasonId, 
    @WebParam(partName = "episodeId", name = "EpisodeID") EpisodeID episodeId) throws InvalidInputException, NotFoundException
    {
        try
        {
            long serId = Long.valueOf(seriesId.getId());
            long sesId = Long.valueOf(seasonId.getId());
            long epId = Long.valueOf(episodeId.getId());
            Episode value = seriesService.getEpisodeFromSeasonOfSeries(serId, sesId, epId);
            if(value == null)
            {
                throw new NotFoundException("Input error", "Either the series with Id=" + serId + " does not exist, or"
                + " the season with Id=" + sesId + " is not added to this series or" + " the episode with Id=" + epId
                + " is not added to the season.");
            }
            else
            {
                Date releaseDate = value.getReleaseDate();
                String toSet = formatDate(releaseDate);
                EpisodePojo pojo = new EpisodePojo(value.getId(), sesId, value.getRating(), 
                value.getDescription(), toSet, value.getNumber());
                return pojo;
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Input error", "The provided input combination for seriesId, seasonId, and episodeId"
            + " does not represent a valid Id format.");
        }
    }

    @WebMethod(action = "post_episode_toSeason_soap", operationName = "PostEpisode")
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse postEpisodeToSeasonSoap(@WebParam(partName = "seriesId", name = "SeriesID") SeriesID seriesId,
    @WebParam(partName = "seasonId", name = "SeasonID") SeasonID seasonId, 
    @WebParam(partName = "Episode", name = "EpisodePojo") EpisodePojo episode,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        if((episode.getDescription() == "")
        || (episode.getRating() == 0.0f) || (episode.getNumber() == 0) || (episode.getReleaseDate() == ""))
        {
            throw new InvalidInputException("Input error", "All detail fields for the episode must be filled.");
        }
        else
        {
            try
            {
                long serId= Long.valueOf(seriesId.getId());
                long sesId = Long.valueOf(seasonId.getId());
                String releaseDate = episode.getReleaseDate();
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
                        Episode toAdd = new Episode(0, sesId, episode.getRating(),
                        episode.getDescription(), toComp, episode.getNumber());
                        Episode value = seriesService.addEpisodeToSeasonOfSeries(serId, sesId, toAdd);
                        if(value == null)
                        {
                            throw new InvalidInputException("Episode error", "This episode already exists in the database or"
                            + " the series with Id=" + serId + " does not exist in the database or the season with Id=" + sesId
                            + " is not added to this series.");
                        }
                        else
                        {
                            return new SoapResponse("Episode with Id=" + value.getId() + " has been added to the season with Id=" + sesId + ".");
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
            catch(NumberFormatException e)
            {
                e.printStackTrace();
                throw new InvalidInputException("Input error", "The provided input combination for seriesId and seasonId"
                + " does not represent a valid Id format.");
            }
        }
    }

    @WebMethod(action = "update_episode_ofSeason_soap", operationName = "UpdateEpisode")
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse updateEpisodeOfSeasonSoap(@WebParam(partName = "seriesId", name = "SeriesID") SeriesID seriesId,
    @WebParam(partName = "seasonId", name = "SeasonID") SeasonID seasonId, 
    @WebParam(partName = "Episode", name = "EpisodePojo") EpisodePojo episode,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotFoundException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        if((episode.getDescription() == "")
        || (episode.getRating() == 0.0f) || (episode.getReleaseDate() == ""))
        {
            throw new InvalidInputException("Input error", "All detail fields for the episode must be filled except for the episode's number.");
        }
        else
        {
            try
            {
                long serId= Long.valueOf(seriesId.getId());
                long sesId = Long.valueOf(seasonId.getId());
                String releaseDate = episode.getReleaseDate();
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
                        Episode toAdd = new Episode(episode.getId(), sesId, episode.getRating(),
                        episode.getDescription(), toComp, episode.getNumber());
                        Episode value = seriesService.updateEpisodeFromSeasonOfSeries(serId, sesId, toAdd);
                        if(value == null)
                        {
                            throw new NotFoundException("Episode error", "This episode does not exist in the database or"
                            + " the series with Id=" + serId + " does not exist in the database or the season with Id=" + sesId
                            + " is not added to this series.");
                        }
                        else
                        {
                            return new SoapResponse("Episode with Id=" + value.getId() + " has been updated to the season with Id=" + sesId + ".");
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
            catch(NumberFormatException e)
            {
                e.printStackTrace();
                throw new InvalidInputException("Input error", "The provided input combination for seriesId and seasonId"
                + " does not represent a valid Id format.");
            }
        }
    }

    @WebMethod(action = "delete_episode_soap", operationName = "DeleteEpisode")
    @WebResult(partName = "Result", name = "SoapResponse")
    public SoapResponse deleteEpisodeSoap(@WebParam(partName = "seriesId", name = "SeriesID") SeriesID seriesId,
    @WebParam(partName = "seasonId", name = "SeasonID") SeasonID seasonId, 
    @WebParam(partName = "episodeId", name = "EpisodeID") EpisodeID episodeId,
    @WebParam(partName = "TokenPart", name = "TokenPojo") TokenPojo token) throws InvalidInputException, NotFoundException, NotAuthorizedException
    {
        if(!token.getToken().equals(adminToken))
        {
            throw new NotAuthorizedException("Authorization error", "You must provide an admin token to do this operation.");
        }
        try
        {
            long serId = Long.valueOf(seriesId.getId());
            long sesId = Long.valueOf(seasonId.getId());
            long epId = Long.valueOf(episodeId.getId());
            boolean value = seriesService.deleteEpisodeFromSeasonOfSeries(serId, sesId, epId);
            if(value)
            {
                return new SoapResponse("Episode with Id=" + epId + " has been removed from the season with Id=" + sesId + ".");
            }
            else
            {
                throw new NotFoundException("Input error", "Either the series with Id=" + serId + " does not exist, or"
                + " the season with Id=" + sesId + " is not added to this series or" + " the episode with Id=" + epId
                + " is not added to the season.");
            }
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            throw new InvalidInputException("Input error", "The provided input combination for seriesId, seasonId, and episodeId"
            + " does not represent a valid Id format.");
        }
    }
    
    private String formatDate(Date date)
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
