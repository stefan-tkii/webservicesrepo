package webproject.filmreview.Models;

import java.sql.Date;

public class WatchesEpisode 
{

    private long Id;
    private User user;
    private Episode episode;
    private Boolean status;
    private Date finishAt;
    
    public WatchesEpisode() 
    { }

    public WatchesEpisode(long Id, User user, Episode episode) 
    {
        this.Id = Id;
        this.user = user;
        this.episode = episode;
        this.finishAt = null;
        this.status = false;
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

    public Episode getEpisode() 
    {
        return this.episode;
    }

    public void setEpisode(Episode episode) 
    {
        this.episode = episode;
    }

    public Boolean getStatus() 
    {
        return this.status;
    }

    public Date getFinishAt() 
    {
        return this.finishAt;
    }

    public void setAsFinished(Date finishDate)
    {
        this.finishAt = finishDate;
        this.status = true;
    }

}