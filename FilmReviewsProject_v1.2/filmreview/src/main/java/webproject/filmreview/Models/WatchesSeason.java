package webproject.filmreview.Models;

import java.util.Date;
import java.util.List;

public class WatchesSeason 
{
    private long Id;
    private long watchesSeriesId;
    private long seasonId;
    private Boolean status;
    private Date finishedAt;
    private List<WatchesEpisode> episodes;

    public WatchesSeason() {
    }

    public WatchesSeason(long Id, long watchesSeriesId, long seasonId, List<WatchesEpisode> episodes) {
        this.Id = Id;
        this.watchesSeriesId = watchesSeriesId;
        this.seasonId = seasonId;
        this.status = false;
        this.finishedAt = null;
        this.episodes = episodes;
    }

    public long getId() {
        return this.Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getWatchesSeriesId() {
        return this.watchesSeriesId;
    }

    public void setWatchesSeriesId(long watchesSeriesId) {
        this.watchesSeriesId = watchesSeriesId;
    }

    public long getSeasonId() {
        return this.seasonId;
    }

    public void setSeasonId(long seasonId) {
        this.seasonId = seasonId;
    }

    public Boolean isStatus() {
        return this.status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public Date getFinishedAt() {
        return this.finishedAt;
    }

    public List<WatchesEpisode> getEpisodes() {
        return this.episodes;
    }

    public void setEpisodes(List<WatchesEpisode> episodes) {
        this.episodes = episodes;
    }

    public void setAsFinished(Date finishDate)
    {
        this.status = true;
        this.finishedAt = finishDate;
    }

}