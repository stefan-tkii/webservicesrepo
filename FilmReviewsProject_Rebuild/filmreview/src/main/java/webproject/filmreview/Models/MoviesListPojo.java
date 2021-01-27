package webproject.filmreview.Models;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MoviesListObject")
@XmlAccessorType(XmlAccessType.FIELD)
public class MoviesListPojo implements Serializable
{
    private static final long serialVersionUID = 1590306082624585638L;

    @XmlElementWrapper(name="Movies")
    @XmlElement(name="Movie")
    private List<Movie> moviesList;

    public MoviesListPojo()
    {

    }

    public List<Movie> getMoviesList() 
    {
        return this.moviesList;
    }

    public void setMoviesList(List<Movie> moviesList) 
    {
        this.moviesList = moviesList;
    }

}