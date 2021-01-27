package webproject.filmreview.Models;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Movie")
@XmlAccessorType(XmlAccessType.FIELD)
public class MoviePojo implements Serializable
{
    private static final long serialVersionUID = 1L;

    public MoviePojo()
    {

    }

    public MoviePojo(long Id, String name, String releaseDate, float rating, String description, String mainActors, List<String> genres) 
    {
        this.Id = Id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.description = description;
        this.mainActors = mainActors;
        this.genres = genres;
    }

    @XmlAttribute
    private long Id;

    private String name;
    private String releaseDate;
    private float rating;
    private String description;
    private String mainActors;

    @XmlElementWrapper(name="Genres")
    @XmlElement(name="Genre")
    private List<String> genres;  
    
    public long getId() {
        return this.Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainActors() {
        return this.mainActors;
    }

    public void setMainActors(String mainActors) {
        this.mainActors = mainActors;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

}