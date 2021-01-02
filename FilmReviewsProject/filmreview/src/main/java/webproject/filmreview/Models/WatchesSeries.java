package webproject.filmreview.Models;

import java.util.Date;
import java.util.List;

public class WatchesSeries 
{

    private long Id;
    private User user;
    private Series series; 
    private List<WatchesSeason> watchedSeasons;
    private Boolean status;
    private Date finishedAt;

    public WatchesSeries() 
    { }

    public WatchesSeries(long Id, User user, Series series, List<WatchesSeason> watchedSeasons) 
    {
        this.Id = Id;
        this.user = user;
        this.series = series;
        this.watchedSeasons = watchedSeasons;
        this.status =false;
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

    public Series getSeries() 
    {
        return this.series;
    }

    public void setSeries(Series series) 
    {
        this.series = series;
    }

    public List<WatchesSeason> getWatchedSeasons() 
    {
        return this.watchedSeasons;
    }

    public void setWatchedSeasons(List<WatchesSeason> watchedSeasons) 
    {
        this.watchedSeasons = watchedSeasons;
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