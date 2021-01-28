package webproject.filmreview.Models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EpisodePojo implements Serializable
{
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    private long Id;

    private long seasonId;
    private float rating;
    private String description;
    private String releaseDate;
    private int number;

    public EpisodePojo()
    {

    }

    public EpisodePojo(long Id, long seasonId, float rating, String description, String releaseDate, int number) {
        this.Id = Id;
        this.seasonId = seasonId;
        this.rating = rating;
        this.description = description;
        this.releaseDate = releaseDate;
        this.number = number;
    }

    public long getId() {
        return this.Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getSeasonId() {
        return this.seasonId;
    }

    public void setSeasonId(long seasonId) {
        this.seasonId = seasonId;
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

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}