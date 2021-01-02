package webproject.filmreview.Models;

import java.util.Date;

public class Episode 
{

    private long Id;
    private Season season;
    private float rating;
    private String description;
    private Date releaseDate;

    public Episode() 
    { }

    public Episode(long Id, Season season, float rating, String description, Date releaseDate) 
    {
        this.Id = Id;
        this.season = season;
        this.rating = rating;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public long getId() 
    {
        return this.Id;
    }

    public void setId(long Id) 
    {
        this.Id = Id;
    }

    public Season getSeason() 
    {
        return this.season;
    }

    public void setSeason(Season season) 
    {
        this.season = season;
    }

    public float getRating() 
    {
        return this.rating;
    }

    public void setRating(float rating) 
    {
        this.rating = rating;
    }

    public String getDescription() 
    {
        return this.description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public Date getReleaseDate() 
    {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) 
    {
        this.releaseDate = releaseDate;
    }

}