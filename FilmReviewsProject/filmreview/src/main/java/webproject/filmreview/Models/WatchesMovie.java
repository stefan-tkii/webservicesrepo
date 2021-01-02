package webproject.filmreview.Models;

import java.util.Date;

public class WatchesMovie 
{

    private long Id;
    private User user;
    private Movie movie;
    private Boolean status;
    private Date finishedAt; 

    public WatchesMovie() { }

    public WatchesMovie(long Id, User user, Movie movie) 
    {
        this.Id = Id;
        this.user = user;
        this.movie = movie;
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

    public Movie getMovie() 
    {
        return this.movie;
    }

    public void setMovie(Movie movie) 
    {
        this.movie = movie;
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
        this.finishedAt = finishDate;
        this.status = true;
    }

}