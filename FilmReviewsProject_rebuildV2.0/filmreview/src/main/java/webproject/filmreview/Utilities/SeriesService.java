package webproject.filmreview.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import webproject.filmreview.Models.Episode;
import webproject.filmreview.Models.Season;
import webproject.filmreview.Models.Series;

public class SeriesService 
{
    private static Map<Long, Series> series = Database.getAllSeries();

    public SeriesService()
    {
       
    }

    public List<Series> getAllSeries()
    {
        return new ArrayList<Series>(series.values());
    }

    public Series getSeriesById(long seriesId)
    {
        if (seriesId == 0l) 
        {
            return null;
        }
        Series ser = series.get(seriesId);
        return ser;
    }

    public Series getSeriesByName(String name)
    {
        if(name == "")
        {
            return null;
        }
        List<Series> seriesList = new ArrayList<>(series.values());
        for(Series ser : seriesList)
        {
            if(ser.getName().equalsIgnoreCase(name))
            {
                return ser;
            }
        }
        return null;
    }

    public Series addSeries(Series seriesToAdd)
    {
        List<Series> seriesList = new ArrayList<>(series.values());
        for(Series ser: seriesList)
        {
            if(ser.getName().equalsIgnoreCase(seriesToAdd.getName()))
            {
                return null;
            }
        }
        seriesToAdd.setId(series.size()+1);
        series.put(seriesToAdd.getId(), seriesToAdd);
        return seriesToAdd;
    }

    public Series updateSeries(Series seriesToUpdate)
    {
        if(seriesToUpdate.getId() == 0l)
        {
            return null;
        }
        else if(series.containsKey(seriesToUpdate.getId()))
        {
            Series s = series.get(seriesToUpdate.getId());
            List<Season> sesList = s.getSeasons();
            seriesToUpdate.setSeasons(sesList);
            series.put(seriesToUpdate.getId(), seriesToUpdate);
            return seriesToUpdate;
        }
        return null;
    }

    public boolean deleteSeries(long seriesId)
    {
        if (seriesId == 0l) 
        {
            return false;
        }
        if(series.containsKey(seriesId))
        {
            series.remove(seriesId);
            return true;
        }
        else
        {
            return false;
        }
    }

    public Season getSeasonFromSeries(long seriesId, long seasonId)
    {
        if((seriesId == 0l) || (seasonId == 0l))
        {
            return null;
        }
        Series ser = series.get(seriesId);
        if(ser.equals(null)) 
        { 
            return null; 
        }
        List<Season> sesList = ser.getSeasons();
        for(Season s: sesList)
        {
            if(s.getId() == seasonId)
            {
                return s;
            }
        }
        return null;
    }

    public Season addSeasonToSeries(long seriesId, Season season)
    {
        if(seriesId == 0l)
        {
            return null;
        }
        Series ser = series.get(seriesId);
        if(ser.equals(null)) 
        { 
            return null; 
        }
        List<Season> sesList = ser.getSeasons();
        for(Season s : sesList)
        {
            if(s.getSeasonNumber() == season.getSeasonNumber())
            {
                return null;
            }
        }
        season.setId(sesList.size()+1);
        season.setSeriesId(seriesId);
        sesList.add(season);
        ser.setSeasons(sesList);
        series.put(ser.getId(), ser);
        return season;
    }

    public Season updateSeasonOfSeries(long seriesId, Season season)
    {
        if(seriesId == 0l)
        {
            return null;
        }
        else if(series.containsKey(seriesId))
        {
            Series s = series.get(seriesId);
            List<Season> sesList = s.getSeasons();
            int i = 0;
            for(Season ses: sesList)
            {
                if(ses.getId() == season.getId())
                {
                    List<Episode> epsList = ses.getEpisodes();
                    Season toAdd = new Season(ses.getId(), ses.getSeasonNumber(), 
                    seriesId, season.getRating(), season.getDescription(), epsList, season.getMainActors());
                    sesList.set(i, toAdd);
                    s.setSeasons(sesList);
                    series.put(seriesId, s);
                    return season;
                }
                i++;
            }
        }
        return null;
    }

    public boolean deleteSeasonFromSeries(long seriesId, long seasonId)
    {
        if((seriesId == 0l) || (seasonId == 0l))
        {
            return false;
        }
        Series ser = series.get(seriesId);
        if(ser.equals(null)) 
        { 
            return false; 
        }
        List<Season> sList = ser.getSeasons();
        for(Season s : sList)
        {
            if(s.getId() == seasonId)
            {
                sList.remove(s);
                ser.setSeasons(sList);
                series.put(seriesId, ser);
                return true;
            }
        }
        return false;
    }

    public Episode getEpisodeFromSeasonOfSeries(long seriesId, long seasonId, long episodeId)
    {
        if((seriesId == 0l) || (seasonId == 0l) || (episodeId == 0l))
        {
            return null;
        }
        Series ser = series.get(seriesId);
        if(ser.equals(null)) 
        { 
            return null; 
        }
        List<Season> sesList = ser.getSeasons();
        for(Season s: sesList)
        {
            if(s.getId() == seasonId)
            {
                List<Episode> epsList = s.getEpisodes();
                for(Episode e : epsList)
                {
                    if(e.getId() == episodeId)
                    {
                        return e;
                    }
                }
            }
        }
        return null;
    }

    public Episode addEpisodeToSeasonOfSeries(long seriesId, long seasonId, Episode episode)
    {
        if((seriesId == 0l) || (seasonId == 0l))
        {
            return null;
        }
        Series ser = series.get(seriesId);
        if(ser.equals(null)) 
        { 
            return null; 
        }
        List<Season> sList = ser.getSeasons();
        int i = 0;
        for(Season s : sList)
        {
            if(s.getId() == seasonId)
            {
                List<Episode> epsList = s.getEpisodes();
                for(Episode ep : epsList)
                {
                    if(ep.getNumber() == episode.getNumber())
                    {
                        return null;
                    }
                }
                episode.setId(epsList.size()+1);
                episode.setSeasonId(s.getId());
                epsList.add(episode);
                s.setEpisodes(epsList);
                sList.set(i, s);
                ser.setSeasons(sList);
                series.put(seriesId, ser);
                return episode;
            }
            i++;
        }
        return null;                                                                 
    }

    public Episode updateEpisodeFromSeasonOfSeries(long seriesId, long seasonId, Episode episode)
    {
        if((seriesId == 0l) || (seasonId == 0l))
        {
            return null;
        }
        else if(series.containsKey(seriesId))
        {
            Series s = series.get(seriesId);
            List<Season> sesList = s.getSeasons();
            int i = 0;
            for(Season ses: sesList)
            {
                if(ses.getId() == seasonId)
                {
                    List<Episode> epsList = ses.getEpisodes();
                    int j = 0;
                    for(Episode e : epsList)
                    {
                        if(e.getId() == episode.getId())
                        {
                            Episode toAdd = new Episode(e.getId(), episode.getSeasonId(), 
                            episode.getRating(), episode.getDescription(), episode.getReleaseDate(), e.getNumber());
                            epsList.set(j, toAdd);
                            ses.setEpisodes(epsList);
                            sesList.set(i, ses);
                            s.setSeasons(sesList);
                            series.put(seriesId, s);
                            return episode;
                        }
                    }
                    j++;
                }
                i++;
            }
        }
        return null;
    }

    public Boolean deleteEpisodeFromSeasonOfSeries(long seriesId, long seasonId, long episodeId)
    {
        if((seriesId == 0l) || (seasonId == 0l) || (episodeId == 0l))
        {
            return false;
        }
        Series ser = series.get(seriesId);
        if(ser.equals(null)) 
        { 
            return false; 
        }
        List<Season> sList = ser.getSeasons();
        int i = 0;
        for(Season s : sList)
        {
            if(s.getId() == seasonId)
            {
                List<Episode> epsList = s.getEpisodes();
                for(Episode p : epsList)
                {
                    if(p.getId() == episodeId)
                    {
                        epsList.remove(p);
                        s.setEpisodes(epsList);
                        sList.set(i, s);
                        ser.setSeasons(sList);
                        series.put(seriesId, ser);
                        return true;
                    }
                }
            }
            i++;
        }
        return false;
    }
    
}