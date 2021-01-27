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

    public MovieService() {
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
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<Movie>(movies.values());
    }

    public Movie getMovieById(long movieId) {
        if (movieId == 0l) {
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