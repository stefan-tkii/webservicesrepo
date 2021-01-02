package webproject.filmreview.Models;

import java.util.List;

public class Season 
{

    private long Id;
    private int seasonNumber;
    private Series series;
    private float rating;
    private String description;
    private List<Episode> episodes;
    private String mainActors;  
    
    public Season() 
    { }

    public Season(long Id, int seasonNumber, Series series, float rating, String description, List<Episode> episodes, String mainActors) 
    {
        this.Id = Id;
        this.seasonNumber = seasonNumber;
        this.series = series;
        this.rating = rating;
        this.description = description;
        this.episodes = episodes;
        this.mainActors = mainActors;
    }

    public long getId() 
    {
        return this.Id;
    }

    public void setId(long Id) 
    {
        this.Id = Id;
    }

    public int getSeasonNumber() 
    {
        return this.seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) 
    {
        this.seasonNumber = seasonNumber;
    }

    public Series getSeries() 
    {
        return this.series;
    }

    public void setSeries(Series series) 
    {
        this.series = series;
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

    public List<Episode> getEpisodes() 
    {
        return this.episodes;
    }

    public void setEpisodes(List<Episode> episodes) 
    {
        this.episodes = episodes;
    }

    public String getMainActors() 
    {
        return this.mainActors;
    }

    public void setMainActors(String mainActors) 
    {
        this.mainActors = mainActors;
    }

}