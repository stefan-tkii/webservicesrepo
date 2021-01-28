package webproject.filmreview.Models;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Series")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeriesPojo implements Serializable
{
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    private long Id;

    private String name;
    private float rating;
    private String description;

    @XmlElementWrapper(name = "SeasonsList")
    @XmlElement(name = "Season")
    private List<Season> seasons;

    @XmlElementWrapper(name="Genres")
    @XmlElement(name="Genre")
    private List<String> genres;
    
    public SeriesPojo()
    {

    }

    public SeriesPojo(long Id, String name, float rating, String description, List<Season> seasons, List<String> genres) 
    {
        this.Id = Id;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.seasons = seasons;
        this.genres = genres;
    }

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

    public List<Season> getSeasons() {
        return this.seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

}