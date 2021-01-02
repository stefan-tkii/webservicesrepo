package webproject.filmreview.Models;

import java.util.Date;
import java.util.List;

public class WatchesSeason 
{

    private long Id;
    private User user;
    private Season season;
    private List<WatchesEpisode> watchedEpisodes;    
    private Boolean status;
    private Date finishedAt;

    public WatchesSeason() 
    { }

    public WatchesSeason(long Id, User user, Season season, List<WatchesEpisode> watchedEpisodes) 
    {
        this.Id = Id;
        this.user = user;
        this.season = season;
        this.watchedEpisodes = watchedEpisodes;
        this.status = false;
        this.finishedAt = null;
    }

    public long getId() 
    {
        return this.Id;
    }

    public void setId(long Id) 
    {
        this.Id = Id;
    }

    public User getUser() 
    {
        return this.user;
    }

    public void setUser(User user) 
    {
        this.user = user;
    }

    public Season getSeason() 
    {
        return this.season;
    }

    public void setSeason(Season season) 
    {
        this.season = season;
    }

    public List<WatchesEpisode> getWatchedEpisodes() 
    {
        return this.watchedEpisodes;
    }

    public void setWatchedEpisodes(List<WatchesEpisode> watchedEpisodes) 
    {
        this.watchedEpisodes = watchedEpisodes;
    }

    public Boolean getStatus() 
    {
        return this.status;
    }

    public Date getFinishedAt() 
    {
        return this.finishedAt;
    }

    public void setAsFinished(Date finishDate)
    {
        this.status = true;
        this.finishedAt = finishDate;
    }

}