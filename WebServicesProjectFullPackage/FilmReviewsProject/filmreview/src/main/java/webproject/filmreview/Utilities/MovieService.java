package webproject.filmreview.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import webproject.filmreview.Models.Genres;
import webproject.filmreview.Models.Movie;

public class MovieService {
    private Map<Long, Movie> movies = Database.getAllMovies();

    public MovieService() 
    {
       
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<Movie>(movies.values());
    }

    public List<Movie> getAllMoviesFiltered(String filterType, String filterValue)
    {
        List<Movie> list = new ArrayList<>();
        List<Movie> allMovies = new ArrayList<>(movies.values());
        switch(filterType)
        {
            case "Name":
                for(Movie m : allMovies)
                {
                    if(m.getName().contains(filterValue))
                    {
                        list.add(m);
                    }
                }
                break;
            case("Rating"):
                try
                {
                    float rating = Float.valueOf(filterValue);
                    for(Movie m : allMovies)
                    {
                        if(m.getRating() >= rating)
                        {
                            list.add(m);
                        }
                    }
                }
                catch(NumberFormatException e)
                {
                    e.printStackTrace();
                    return list;
                }
                break;
            case("After date"):
                Date afterDate = null;
                try
                {
                    afterDate = new SimpleDateFormat("dd/MM/yyyy").parse(filterValue);
                    for(Movie m : allMovies)
                    {
                        if(m.getReleaseDate().after(afterDate))
                        {
                            list.add(m);
                        }
                    }
                }
                catch(ParseException e)
                {
                    e.printStackTrace();
                    return list;
                }
                break;
            case("Before date"):
                Date beforeDate = null;
                try
                {
                    beforeDate = new SimpleDateFormat("dd/MM/yyyy").parse(filterValue);
                    for(Movie m : allMovies)
                    {
                        if(m.getReleaseDate().before(beforeDate))
                        {
                            list.add(m);
                        }
                    }
                }
                catch(ParseException e)
                {
                    e.printStackTrace();
                    return list;
                }
                break;
            case("Genre"):
                for(Movie m : allMovies)
                {
                    List<Genres> gnrs = m.getGenres();
                    for(Genres g : gnrs)
                    {
                        if(filterValue.equalsIgnoreCase(g.name()))
                        {
                            list.add(m);
                            break;
                        }
                    }
                }
                break;
            default:
                break;
        }
        return list;
    }

    public Movie getMovieById(long movieId) 
    {
        if (movieId == 0l) 
        {
            return null;
        }
        Movie mov = movies.get(movieId);
        return mov;
    }

    public Movie getMovieByName(String name)
    {
        if(name == "")
        {
            return null;
        }
        List<Movie> movs = new ArrayList<>(movies.values());
        for(Movie m : movs)
        {
            if(m.getName().equalsIgnoreCase(name))
            {
                return m;
            }
        }
        return null;
    }

    public Movie addMovie(Movie movie) {
        List<Movie> moviesList = new ArrayList<>(movies.values());
        for(Movie mov: moviesList)
        {
            if(mov.getName().equalsIgnoreCase(movie.getName()))
            {
                return null;
            }
        }
        movie.setId(movies.size()+1);
        movies.put(movie.getId(), movie);
        return movie;
    }

    public Movie updateMovie(Movie movie)
    {
        if(movie.getId() <=0 )
        {
            return null;
        }
        else if(movies.containsKey(movie.getId()))
        {
            movies.put(movie.getId(), movie);
            return movie;
        }
        return null;
    }

    public boolean deleteMovie(long movieId)
    {
        if (movieId == 0l) 
        {
            return false;
        }
        if(movies.containsKey(movieId))
        {
            movies.remove(movieId);
            return true;
        }
        else
        {
            return false;
        }
    }

}