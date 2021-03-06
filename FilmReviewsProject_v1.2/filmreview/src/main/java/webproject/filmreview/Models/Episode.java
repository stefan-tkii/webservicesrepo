package webproject.filmreview.Models;

import java.util.Date;

public class Episode 
{

    private long Id;
    private long seasonId;
    private float rating;
    private String description;
    private Date releaseDate;
    private int number;

    public Episode() 
    { }

    public Episode(long Id, long seasonId, float rating, String description, Date releaseDate, int number) 
    {
        this.Id = Id;
        this.seasonId = seasonId;
        this.rating = rating;
        this.description = description;
        this.releaseDate = releaseDate;
        this.number = number;
    }

    public int getNumber()
    {
        return this.number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public long getId() 
    {
        return this.Id;
    }

    public void setId(long Id) 
    {
        this.Id = Id;
    }

    public long getSeasonId() 
    {
        return this.seasonId;
    }

    public void setSeasonId(long seasonId) 
    {
        this.seasonId = seasonId;
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